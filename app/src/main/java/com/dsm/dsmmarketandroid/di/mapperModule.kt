package com.dsm.dsmmarketandroid.di

import com.dsm.dsmmarketandroid.presentation.mapper.*
import org.koin.dsl.module

val mapperModule = module {
    factory { PostCategoryModelMapper() }

    factory { ProductModelMapper() }

    factory { PurchaseDetailModelMapper() }

    factory { RentDetailModelMapper() }

    factory { CommentModelMapper() }

    factory { RecentMapper() }

    factory { SearchHistoryModelMapper() }

    factory { RecommendModelMapper() }
}