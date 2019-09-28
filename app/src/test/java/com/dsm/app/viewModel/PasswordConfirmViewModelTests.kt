package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PasswordConfirmViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var confirmPasswordUseCase: ConfirmPasswordUseCase

    private lateinit var viewModel: PasswordConfirmViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = PasswordConfirmViewModel(confirmPasswordUseCase)
    }
}
