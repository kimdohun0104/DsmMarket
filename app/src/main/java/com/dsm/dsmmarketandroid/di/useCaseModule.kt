package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }

    factory { AutoLoginUseCase(get()) }

    factory { SignUpUseCase(get()) }

    factory { RefreshTokenUseCase(get()) }

    factory { SendPasswordCodeUseCase(get()) }

    factory { PasswordCodeConfirmUseCase(get()) }

    factory { ChangePasswordUseCase(get()) }

    factory { ChangeNickUseCase(get()) }

    factory { GetPostCategoryUseCase(get()) }

    factory { PostRentUseCase(get()) }

    factory { PostPurchaseUseCase(get()) }

    factory { ConfirmPasswordUseCase(get()) }

    factory { GetPurchaseDetailUseCase(get()) }

    factory { GetRentDetailUseCase(get()) }

    factory { PostCommentUseCase(get()) }

    factory { GetCommentUseCase(get()) }

    factory { InterestUseCase(get()) }

    factory { UnInterestUseCase(get()) }

    factory { GetRecentPurchaseUseCase(get()) }

    factory { GetRecentRentUseCase(get()) }

    factory { GetSearchHistoryUseCase(get()) }

    factory { DeleteSearchHistoryUseCase(get()) }

    factory { GetInterestUseCase(get()) }

    factory { GetMyPurchaseUseCase(get())}

    factory { GetMyRentUseCase(get()) }

    factory { ReportPostUseCase(get()) }

    factory { ReportCommentUseCase(get()) }

    factory { GetRecommendUseCase(get()) }

    factory { GetRelatedUseCase(get()) }

    factory { CompletePurchaseUseCase(get()) }

    factory { CompleteRentUseCase(get()) }

    factory { ModifyPurchaseUseCase(get()) }

    factory { ModifyRentUseCase(get()) }

    factory { GetRentImageUseCase(get()) }

    factory { GetPurchaseImageUseCase(get()) }
}