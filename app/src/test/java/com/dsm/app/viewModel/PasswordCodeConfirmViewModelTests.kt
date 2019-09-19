package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.PasswordCodeConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm.PasswordCodeConfirmViewModel
import com.jraska.livedata.test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PasswordCodeConfirmViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var passwordCodeConfirmUseCase: PasswordCodeConfirmUseCase

    private lateinit var viewModel: PasswordCodeConfirmViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
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
}