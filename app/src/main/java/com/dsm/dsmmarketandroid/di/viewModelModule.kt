package com.dsm.dsmmarketandroid.di

import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickViewModel
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.MeViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm.PasswordCodeConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.sendPasswordCode.SendPasswordCodeViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpViewModel
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { SendPasswordCodeViewModel(get()) }

    viewModel { PasswordCodeConfirmViewModel(get()) }

    viewModel { ChangePasswordViewModel(get()) }

    viewModel { MeViewModel(get()) }

    viewModel { ChangeNickViewModel(get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { PostCategoryViewModel(get(), get()) }

    viewModel { PostPurchaseViewModel() }
}