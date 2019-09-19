package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
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

    @Test
    fun confirmPasswordSuccess() {
        val response = Pair("email", 0)
        viewModel.originalPassword.value = "ORIGINAL_PASSWORD"
        `when`(confirmPasswordUseCase.create(viewModel.originalPassword.value!!))
            .thenReturn(Flowable.just(response))

        viewModel.confirmPassword()

        viewModel.intentChangePassword.test().assertValue(response)
        viewModel.finishActivityEvent.test().assertHasValue()
    }
}
