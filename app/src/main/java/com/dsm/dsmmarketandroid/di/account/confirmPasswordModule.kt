package com.dsm.dsmmarketandroid.di.account

import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.me.password.passwordConfirm.ConfirmPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val confirmPasswordModule = module {

    factory { ConfirmPasswordUseCase(get()) }

    viewModel { ConfirmPasswordViewModel(get()) }
}