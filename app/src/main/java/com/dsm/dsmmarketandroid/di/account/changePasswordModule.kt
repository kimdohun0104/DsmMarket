package com.dsm.dsmmarketandroid.di.account

import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.me.password.changePassword.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changePasswordModule = module {

    factory { ChangePasswordUseCase(get()) }

    viewModel { ChangePasswordViewModel(get()) }
}