package com.dsm.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.PasswordCodeConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm.PasswordCodeConfirmViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PasswordCodeConfirmViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var passwordCodeConfirmUseCase: PasswordCodeConfirmUseCase
    private lateinit var viewModel: PasswordCodeConfirmViewModel

    @Before
    fun init() {
        passwordCodeConfirmUseCase = mock(PasswordCodeConfirmUseCase::class.java)
        viewModel = PasswordCodeConfirmViewModel(passwordCodeConfirmUseCase)
    }

    @Test
    fun `when there's no blank isConfirmPasswordCodeEnable == true`() {
        viewModel.passwordCode.value = "0000"

        assertTrue(viewModel.isConfirmPasswordCodeEnable.test().value())
    }

    @Test
    fun `when there's blank isConfirmPasswordCodeEnable == false`() {
        viewModel.passwordCode.value = ""

        assertFalse(viewModel.isConfirmPasswordCodeEnable.test().value())
    }

    @Test
    fun `confirm password code success (200)`() {
        viewModel.passwordCode.value = "1234567"

        `when`(
            passwordCodeConfirmUseCase.create(
                hashMapOf(
                    "email" to "email",
                    "code" to viewModel.passwordCode.value!!.toInt()
                )
            )
        ).thenReturn(Flowable.just(200))

        viewModel.confirmPasswordCode("email")

        viewModel.intentChangePasswordWithEmail.test().assertHasValue()
    }

    @Test
    fun `invalid confirm code (403)`() {
        viewModel.passwordCode.value = "1234567"

        `when`(
            passwordCodeConfirmUseCase.create(
                hashMapOf(
                    "email" to "email",
                    "code" to viewModel.passwordCode.value!!.toInt()
                )
            )
        ).thenReturn(Flowable.just(403))

        viewModel.confirmPasswordCode("email")

        viewModel.toastConfirmCodeFailEvent.test().assertHasValue()
    }
}