package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.SendTempPasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.me.password.forgotPassword.ForgotPasswordViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ForgotPasswordViewModelTests : BaseTest() {

    @Mock
    private lateinit var sendTempPasswordUseCase: SendTempPasswordUseCase

    private lateinit var viewModel: ForgotPasswordViewModel

    @Before
    fun init() {
        viewModel = ForgotPasswordViewModel(sendTempPasswordUseCase)
    }

    @Test
    fun `send password btn test`() {
        viewModel.run {
            email.value = ""

            isSendTempPasswordEnable.test().assertValue(false)

            email.value = "example@example.com"

            isSendTempPasswordEnable.test().assertValue(true)
        }
    }

    @Test
    fun `email invalid test`() {
        viewModel.run {
            email.value = "invalid email"

            sendTempPassword()

            toastEvent.test().assertValue(R.string.fail_invalid_email)
        }
    }

    @Test
    fun `send email success test`() {
        viewModel.run {
            email.value = "email@example.com"

            `when`(sendTempPasswordUseCase.create(email.value!!))
                .thenReturn(Flowable.just(Resource.Success(Unit)))

            sendTempPassword()

            showLoadingDialogEvent.test().assertHasValue()
            toastEvent.test().assertValue(R.string.send_temp_password_success)
            finishActivityEvent.test().assertHasValue()
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }

    @Test
    fun `send email internal error test`() {
        viewModel.run {
            email.value = "email@example.com"

            `when`(sendTempPasswordUseCase.create(email.value!!))
                .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

            sendTempPassword()

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }
}