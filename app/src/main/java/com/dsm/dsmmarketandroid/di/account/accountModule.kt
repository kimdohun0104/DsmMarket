package com.dsm.dsmmarketandroid.di.account

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.dataSource.account.AccountDataSourceImpl
import com.dsm.data.repository.AccountRepositoryImpl
import com.dsm.domain.repository.AccountRepository
import com.dsm.domain.service.AccountService
import com.dsm.domain.service.AccountServiceImpl
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.domain.usecase.SendTempPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.auth.forgotPassword.ForgotPasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.changeNick.ChangeNickViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.password.changePassword.ChangePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    factory<AccountDataSource> { AccountDataSourceImpl(get(), get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get()) }

    factory<AccountService> { AccountServiceImpl(get(), get()) }

    // change nick
    factory { ChangeNickUseCase(get()) }

    viewModel { ChangeNickViewModel(get()) }

    // change password
    factory { ChangePasswordUseCase(get()) }

    viewModel { ChangePasswordViewModel(get()) }

    // forgot password
    factory { SendTempPasswordUseCase(get()) }

    viewModel { ForgotPasswordViewModel(get()) }
}