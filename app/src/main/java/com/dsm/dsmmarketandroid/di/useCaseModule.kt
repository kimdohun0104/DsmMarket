package com.dsm.dsmmarketandroid.di

import com.dsm.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPostCategoryUseCase(get()) }

    factory { PostRentUseCase(get()) }

    factory { PostPurchaseUseCase(get()) }

    factory { PostCommentUseCase(get()) }

    factory { GetCommentUseCase(get()) }

    factory { GetRecentPurchaseUseCase(get()) }

    factory { GetRecentRentUseCase(get()) }

    factory { GetSearchHistoryUseCase(get()) }

    factory { DeleteSearchHistoryUseCase(get()) }

    factory { GetInterestUseCase(get()) }

    factory { GetMyPurchaseUseCase(get())}

    factory { GetMyRentUseCase(get()) }

    factory { ReportPostUseCase(get()) }

    factory { ReportCommentUseCase(get()) }

    factory { CompletePurchaseUseCase(get()) }

    factory { CompleteRentUseCase(get()) }

    factory { ModifyPurchaseUseCase(get()) }

    factory { ModifyRentUseCase(get()) }

    factory { GetRentImageUseCase(get()) }

    factory { GetPurchaseImageUseCase(get()) }

    factory { AddSearchHistoryUseCase(get()) }
}