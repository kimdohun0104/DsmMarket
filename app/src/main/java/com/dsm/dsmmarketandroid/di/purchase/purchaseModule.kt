package com.dsm.dsmmarketandroid.di.purchase

import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.dataSource.purchase.PurchaseDataSourceImpl
import com.dsm.data.mapper.ProductMapper
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.repository.PurchaseRepositoryImpl
import com.dsm.domain.repository.PurchaseRepository
import com.dsm.domain.service.PurchaseService
import com.dsm.domain.service.PurchaseServiceImpl
import org.koin.dsl.module

val purchaseModule = module {

    factory<PurchaseDataSource> { PurchaseDataSourceImpl(get(), get()) }

    factory { ProductMapper() }

    factory<PurchaseRepository> { PurchaseRepositoryImpl(get(), get()) }

    factory<PurchaseService> { PurchaseServiceImpl(get()) }

    factory { (search: String, category: String) -> PurchaseDataFactory(get(), search, category) }
}