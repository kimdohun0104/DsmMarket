package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.purchase.PurchaseKeyedDataSource
import org.koin.dsl.module

val pagingModule = module {
    factory { PurchaseKeyedDataSource(get()) }

    factory { PurchaseDataFactory(get()) }
}