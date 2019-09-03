package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SplashViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: SplashViewModel

    @Before
    fun init() {
        loginUseCase = mock(LoginUseCase::class.java)
        viewModel = SplashViewModel(loginUseCase)
    }

    @Test
    fun `login success (200)`() {
        `when`(loginUseCase.create()).thenReturn(Flowable.just(200))

        viewModel.login()

        viewModel.intentMainActivityEvent.test().assertHasValue()
    }

    @Test
    fun `login failed (401)`() {
        `when`(loginUseCase.create()).thenReturn(Flowable.just(401))

        viewModel.login()

        viewModel.intentLoginActivityEvent.test().assertHasValue()
    }
}