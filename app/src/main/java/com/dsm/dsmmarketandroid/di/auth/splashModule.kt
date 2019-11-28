package com.dsm.dsmmarketandroid.di.auth

import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    factory { AutoLoginUseCase(get()) }

    viewModel { SplashViewModel(get()) }
}