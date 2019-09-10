package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.domain.usecase.GetPostCategoryUseCase
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.GetUserNickUseCase
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.domain.usecase.PasswordCodeConfirmUseCase
import com.dsm.domain.usecase.PostCommentUseCase
import com.dsm.domain.usecase.PostPurchaseUseCase
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.domain.usecase.RefreshTokenUseCase
import com.dsm.domain.usecase.SendPasswordCodeUseCase
import com.dsm.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }

    factory { AutoLoginUseCase(get()) }

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

    factory { ConfirmPasswordUseCase(get()) }

    factory { GetPurchaseDetailUseCase(get()) }

    factory { GetRentDetailUseCase(get()) }

    factory { PostCommentUseCase(get()) }
}