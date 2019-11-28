package com.dsm.dsmmarketandroid.di.auth

import com.dsm.data.dataSource.auth.AuthDataSource
import com.dsm.data.dataSource.auth.AuthDataSourceImpl
import com.dsm.data.mapper.TokenMapper
import com.dsm.data.repository.AuthRepositoryImpl
import com.dsm.domain.repository.AuthRepository
import com.dsm.domain.service.AuthService
import com.dsm.domain.service.AuthServiceImpl
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.ui.auth.login.LoginViewModel
import com.dsm.dsmmarketandroid.presentation.ui.auth.signUp.SignUpViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.password.passwordConfirm.ConfirmPasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    factory<AuthDataSource> { AuthDataSourceImpl(get(), get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory<AuthService> { AuthServiceImpl(get(), get()) }

    factory { RefreshTokenUseCase(get()) }

    // login
    factory { TokenMapper() }

    factory { LoginUseCase(get()) }

    viewModel { LoginViewModel(get()) }

    // signUp
    factory { SignUpUseCase(get()) }

    viewModel { SignUpViewModel(get()) }

    // splash
    factory { AutoLoginUseCase(get()) }

    viewModel { SplashViewModel(get()) }

    // confirm password
    factory { ConfirmPasswordUseCase(get()) }

    viewModel { ConfirmPasswordViewModel(get()) }
}