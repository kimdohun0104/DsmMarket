package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LoginViewModelTests {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: LoginViewModel

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        loginUseCase = mock(LoginUseCase::class.java)
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `when there's no blank isLoginEnable == true`() {
        val isLoginEnable = viewModel.isLoginEnable.test()

        viewModel.email.value = "example@example.com"
        viewModel.password.value = "exampletest"

        assertTrue(isLoginEnable.value())
    }

    @Test
    fun `when there's no blank isLoginEnable == false`() {
        val isLoginEnable = viewModel.isLoginEnable.test()

        viewModel.email.value = "example@example.com"

        assertFalse(isLoginEnable.value())
    }

    @Test
    fun `login success (200)`() {
        viewModel.email.value = "test@test.com"
        viewModel.password.value = "testPassword"

        val request = hashMapOf(
            "email" to viewModel.email.value,
            "password" to viewModel.password.value
        )
        `when`(loginUseCase.create(request)).thenReturn(Flowable.just(Unit))

        viewModel.login()

        viewModel.intentMainActivityEvent.test().assertHasValue()
    }
}