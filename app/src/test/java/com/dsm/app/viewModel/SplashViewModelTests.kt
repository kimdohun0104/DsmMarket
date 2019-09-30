package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SplashViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginUseCase: AutoLoginUseCase

    private lateinit var viewModel: SplashViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = SplashViewModel(loginUseCase)
    }
}