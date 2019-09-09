package com.dsm.dsmmarketandroid.di

import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostCategoryModelMapper() }

    factory { ProductModelMapper() }

    factory { PurchaseDetailModelMapper() }
}