package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.io.IOException


class SplashViewModelTests : BaseTest() {

    @Mock
    private lateinit var loginUseCase: AutoLoginUseCase

    private lateinit var viewModel: SplashViewModel

    @Before
    fun init() {
        viewModel = SplashViewModel(loginUseCase)
    }

    @Test
    fun `auto login success test`() {
        `when`(loginUseCase.create(Unit)).thenReturn(Flowable.just(Unit))

        viewModel.login()

        viewModel.intentMainActivityEvent.test().assertHasValue()
    }

    @Test
    fun `auto login failed test`() {
        `when`(loginUseCase.create(Unit)).thenReturn(Flowable.error(IOException()))

        viewModel.login()
        viewModel.intentStartActivity.test().assertHasValue()
    }
}