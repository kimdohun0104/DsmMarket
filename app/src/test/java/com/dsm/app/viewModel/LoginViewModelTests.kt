package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.InternalException
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
        viewModel.run {
            email.value = "test@a.a"
            password.value = "password"

            val param = hashMapOf(
                "email" to email.value,
                "password" to password.value
            )
            `when`(loginUseCase.create(param)).thenReturn(Flowable.just(Unit))

            login()

            showLoadingDialogEvent.test().assertHasValue()
            hideKeyboardEvent.test().assertHasValue()
        }
    }

    @Test
    fun `login forbidden error test`() {
        viewModel.run {
            email.value = "test@a.a"
            password.value = "password"

            val param = hashMapOf(
                "email" to email.value,
                "password" to password.value
            )
            `when`(loginUseCase.create(param))
                .thenReturn(Flowable.error(ForbiddenException(createHttpException(403))))

            login()

            toastEvent.test().assertValue(R.string.fail_login)
        }
    }

    @Test
    fun `login internal error test`() {
        viewModel.run {
            email.value = "test@a.a"
            password.value = "password"

            val param = hashMapOf(
                "email" to email.value,
                "password" to password.value
            )
            `when`(loginUseCase.create(param))
                .thenReturn(Flowable.error(InternalException(createHttpException(403))))

            login()

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }
}