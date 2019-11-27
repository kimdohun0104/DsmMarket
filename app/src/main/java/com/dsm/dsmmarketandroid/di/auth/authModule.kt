package com.dsm.dsmmarketandroid.di.auth

import com.dsm.data.dataSource.auth.AuthDataSource
import com.dsm.data.dataSource.auth.AuthDataSourceImpl
import com.dsm.data.repository.AuthRepositoryImpl
import com.dsm.domain.repository.AuthRepository
import com.dsm.domain.service.AuthService
import com.dsm.domain.service.AuthServiceImpl
import com.dsm.domain.usecase.RefreshTokenUseCase
import org.koin.dsl.module

val authModule = module {

    factory<AuthDataSource> { AuthDataSourceImpl(get(), get()) }

    factory<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory<AuthService> { AuthServiceImpl(get(), get()) }

    factory { RefreshTokenUseCase(get()) }
}