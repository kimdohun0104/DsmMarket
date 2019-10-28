package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.io.IOException

class PasswordConfirmViewModelTests : BaseTest() {

    @Mock
    private lateinit var confirmPasswordUseCase: ConfirmPasswordUseCase

    private lateinit var viewModel: PasswordConfirmViewModel

    @Before
    fun init() {
        viewModel = PasswordConfirmViewModel(confirmPasswordUseCase)
    }

    @Test
    fun `isConfirmEnable value test`() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            assertTrue(isConfirmEnable.test().value())

            originalPassword.value = ""
            assertFalse(isConfirmEnable.test().value())
        }
    }

    @Test
    fun `confirm password success test`() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"

            `when`(confirmPasswordUseCase.create(originalPassword.value!!))
                .thenReturn(Flowable.just(Unit))

            confirmPassword()

            intentChangePasswordEvent.test().assertHasValue()
            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun `confirm password failed invalid password`() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"

            `when`(confirmPasswordUseCase.create(originalPassword.value!!))
                .thenReturn(Flowable.error(createHttpException(401)))

            confirmPassword()

            toastEvent.test().assertValue(R.string.fail_diff_password)
        }
    }

    @Test
    fun `server error test`() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"

            `when`(confirmPasswordUseCase.create(originalPassword.value!!))
                .thenReturn(Flowable.error(IOException()))

            confirmPassword()

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }
}
