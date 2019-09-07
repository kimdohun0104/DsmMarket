package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.purchase.PurchaseKeyedDataSource
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.data.paging.rent.RentKeyedDataSource
import org.koin.dsl.module

val pagingModule = module {
    factory { PurchaseKeyedDataSource(get()) }

    factory { PurchaseDataFactory(get()) }

    factory { RentKeyedDataSource(get()) }

    factory { RentDataFactory(get()) }
}