package com.dsm.dsmmarketandroid.di.account

import com.dsm.domain.usecase.SendTempPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword.ForgotPasswordViewModel
import org.koin.dsl.module

val forgotPasswordModule = module {

    factory { SendTempPasswordUseCase(get()) }

    factory { ForgotPasswordViewModel(get()) }
}