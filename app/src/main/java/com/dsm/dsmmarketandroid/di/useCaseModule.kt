package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.LoginUseCase
import com.dsm.domain.usecase.RefreshTokenUseCase
import com.dsm.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }

    factory { SignUpUseCase(get()) }

    factory { RefreshTokenUseCase(get()) }
}