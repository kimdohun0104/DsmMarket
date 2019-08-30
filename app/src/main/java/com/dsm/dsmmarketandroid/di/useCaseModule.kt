package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }

    factory { SignUpUseCase(get()) }

    factory { RefreshTokenUseCase(get()) }

    factory { SendMailUseCase(get()) }

    factory { MailConfirmUseCase(get()) }
}