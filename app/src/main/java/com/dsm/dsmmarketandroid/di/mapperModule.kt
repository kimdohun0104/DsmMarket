package com.dsm.dsmmarketandroid.di

import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseModelMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostCategoryModelMapper() }

    factory { PurchaseModelMapper() }
}