package com.dsm.dsmmarketandroid.di.account

import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.presentation.ui.main.me.changeNick.ChangeNickViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val changeNickModule = module {

    factory { ChangeNickUseCase(get()) }

    viewModel { ChangeNickViewModel(get()) }
}