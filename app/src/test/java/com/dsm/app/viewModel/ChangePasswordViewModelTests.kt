package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ChangePasswordViewModelTests : BaseTest() {

    @Mock
    private lateinit var changePasswordUseCase: ChangePasswordUseCase

    private lateinit var viewModel: ChangePasswordViewModel

    @Before
    fun init() {
        viewModel = ChangePasswordViewModel(changePasswordUseCase)
    }

    @Test
    fun `change password button enable test`() {
        viewModel.newPassword.value = "NEW_PASSWORD"
        viewModel.reType.value = "NEW_PASSWORD"

        assertTrue(viewModel.isChangePasswordEnable.test().value())
    }

    @Test
    fun `change password button disable test`() {
        viewModel.newPassword.value = "NEW_PASSWORD"

        assertFalse(viewModel.isChangePasswordEnable.test().value())
    }

    @Test
    fun `input password different test`() {
        viewModel.run {
            newPassword.value = "PASSWORD"
            reType.value = "DIFF_PASSWORD"

            changePassword()

            toastEvent.test().assertValue(R.string.fail_diff_password)
        }
    }

    @Test
    fun `change password success tes`() {
        viewModel.run {
            newPassword.value = "PASSWORD"
            reType.value = "PASSWORD"

            `when`(changePasswordUseCase.create(newPassword.value!!))
                .thenReturn(Flowable.just(Unit))

            viewModel.changePassword()

            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun `server error test`() {
        viewModel.run {
            newPassword.value = "PASSWORD"
            reType.value = "PASSWORD"

            `when`(changePasswordUseCase.create(newPassword.value!!))
                .thenReturn(Flowable.error(Exception()))

            viewModel.changePassword()

            viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }
}