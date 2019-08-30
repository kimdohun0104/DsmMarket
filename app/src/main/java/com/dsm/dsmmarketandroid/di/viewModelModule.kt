package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.MailConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.ui.findPassword.FindPasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginViewModel
import com.dsm.dsmmarketandroid.presentation.ui.mailConfirm.MailConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { FindPasswordViewModel(get()) }

    viewModel { MailConfirmViewModel(get()) }
}