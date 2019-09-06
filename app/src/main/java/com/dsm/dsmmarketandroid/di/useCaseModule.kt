package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }

    factory { SignUpUseCase(get()) }

    factory { RefreshTokenUseCase(get()) }

    factory { SendPasswordCodeUseCase(get()) }

    factory { PasswordCodeConfirmUseCase(get()) }

    factory { ChangePasswordUseCase(get()) }

    factory { GetUserNickUseCase(get()) }

    factory { ChangeNickUseCase(get()) }

    factory { GetPostCategoryUseCase(get()) }

    factory { PostRentUseCase(get()) }

    factory { PostPurchaseUseCase(get()) }
}