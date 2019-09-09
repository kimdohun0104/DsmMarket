package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.AutoLoginUseCase
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

    private lateinit var loginUseCase: AutoLoginUseCase
    private lateinit var viewModel: SplashViewModel

    @Before
    fun init() {
        loginUseCase = mock(AutoLoginUseCase::class.java)
        viewModel = SplashViewModel(loginUseCase)
    }

    @Test
    fun `login success (200)`() {
        `when`(loginUseCase.create(Unit)).thenReturn(Flowable.just(Unit))

        viewModel.login()

        viewModel.intentMainActivityEvent.test().assertHasValue()
    }
}