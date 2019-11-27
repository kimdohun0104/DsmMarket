package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.auth.login.LoginViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.io.IOException

class LoginViewModelTests : BaseTest() {

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun init() {
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `login button enable test`() {
        val isLoginEnable = viewModel.isLoginEnable.test()

        viewModel.email.value = "example@example.com"
        viewModel.password.value = "exampletest"

        assertTrue(isLoginEnable.value())
    }

    @Test
    fun `login button disable test`() {
        val isLoginEnable = viewModel.isLoginEnable.test()

        viewModel.email.value = "example@example.com"

        assertFalse(isLoginEnable.value())
    }

    @Test
    fun `invalid email test`() {
        viewModel.email.value = "test@aaa"
        viewModel.password.value = "testPassword"

        viewModel.login()

        viewModel.toastEvent.test().assertValue(R.string.fail_invalid_email)
    }

    @Test
    fun `login success test`() {
        viewModel.email.value = "test@test.com"
        viewModel.password.value = "testPassword"

        val request = hashMapOf(
            "email" to viewModel.email.value,
            "password" to viewModel.password.value
        )
        `when`(loginUseCase.create(request)).thenReturn(Flowable.just(Unit))

        viewModel.login()

        viewModel.run {
            hideLoadingDialogEvent.test().assertHasValue()
            hideKeyboardEvent.test().assertHasValue()
            intentMainActivityEvent.test().assertHasValue()
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }

    @Test
    fun `server error test`() {
        viewModel.email.value = "test@test.com"
        viewModel.password.value = "testPassword"

        val request = hashMapOf(
            "email" to viewModel.email.value,
            "password" to viewModel.password.value
        )
        `when`(loginUseCase.create(request)).thenReturn(Flowable.error(IOException()))

        viewModel.login()

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
        viewModel.hideLoadingDialogEvent.test().assertHasValue()
    }

    @Test
    fun `incorrect email or password test`() {
        viewModel.email.value = "test@test.com"
        viewModel.password.value = "testPassword"

        val request = hashMapOf(
            "email" to viewModel.email.value,
            "password" to viewModel.password.value
        )
        `when`(loginUseCase.create(request)).thenReturn(Flowable.error(createHttpException(403)))

        viewModel.login()

        viewModel.toastEvent.test().assertValue(R.string.fail_login)
        viewModel.hideLoadingDialogEvent.test().assertHasValue()
    }
}