package com.dsm.dsmmarketandroid.di.auth

import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.presentation.ui.auth.signUp.SignUpViewModel
import org.koin.dsl.module

val signUpModule = module {

    factory { SignUpUseCase(get()) }

    factory { SignUpViewModel(get()) }
}