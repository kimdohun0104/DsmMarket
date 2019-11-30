package com.dsm.dsmmarketandroid.di.product

import com.dsm.data.dataSource.product.ProductDataSource
import com.dsm.data.dataSource.product.ProductDataSourceImpl
import com.dsm.data.mapper.InterestProductMapper
import com.dsm.data.mapper.ProductMapper
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.data.repository.ProductRepositoryImpl
import com.dsm.domain.repository.ProductRepository
import com.dsm.domain.service.ProductService
import com.dsm.domain.service.ProductServiceImpl
import com.dsm.domain.usecase.GetInterestPurchaseUseCase
import com.dsm.domain.usecase.GetInterestRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.me.interest.InterestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {

    factory <ProductDataSource> { ProductDataSourceImpl(get(), get(), get()) }

    factory { ProductMapper() }

    factory { InterestProductMapper() }

    factory<ProductRepository> { ProductRepositoryImpl(get(), get(), get()) }

    factory<ProductService> { ProductServiceImpl(get()) }

    factory { ProductModelMapper() }

    factory { (search: String, category: String) -> PurchaseDataFactory(get(), search, category) }

    factory { (search: String, category: String) -> RentDataFactory(get(), search, category) }

    // interest
    factory { GetInterestPurchaseUseCase(get()) }

    factory { GetInterestRentUseCase(get()) }

    viewModel { InterestViewModel(get(), get(), get()) }
}