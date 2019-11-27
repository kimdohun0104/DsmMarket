package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.auth.AuthDataSource
import com.dsm.data.dataSource.auth.AuthDataSourceImpl
import com.dsm.data.mapper.TokenMapper
import com.dsm.data.repository.AuthRepositoryImpl
import com.dsm.domain.repository.AuthRepository
import com.dsm.domain.service.AuthService
import com.dsm.domain.service.AuthServiceImpl
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.ui.auth.login.LoginViewModel
import org.koin.dsl.module

val loginModule = module {
    factory<AuthDataSource> { AuthDataSourceImpl(get(), get()) }

    factory { TokenMapper() }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory<AuthService> { AuthServiceImpl(get(), get()) }

    factory { LoginUseCase(get()) }

    factory { LoginViewModel(get()) }
}