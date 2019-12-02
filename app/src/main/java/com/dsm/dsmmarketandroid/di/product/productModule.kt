package com.dsm.dsmmarketandroid.di.product

import com.dsm.data.dataSource.product.ProductDataSource
import com.dsm.data.dataSource.product.ProductDataSourceImpl
import com.dsm.data.mapper.InterestProductMapper
import com.dsm.data.mapper.ProductMapper
import com.dsm.data.mapper.RecentMapper
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.data.repository.ProductRepositoryImpl
import com.dsm.domain.repository.ProductRepository
import com.dsm.domain.service.ProductService
import com.dsm.domain.service.ProductServiceImpl
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.me.interest.InterestViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.recent.RecentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseList.PurchaseListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList.RentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {

    factory <ProductDataSource> { ProductDataSourceImpl(get(), get(), get(), get()) }

    factory { ProductMapper() }

    factory { InterestProductMapper() }

    factory { RecentMapper() }

    factory<ProductRepository> { ProductRepositoryImpl(get(), get(), get(), get()) }

    factory<ProductService> { ProductServiceImpl(get(), get()) }

    factory { ProductModelMapper() }

    // purchase list
    factory { (search: String, category: String) -> PurchaseDataFactory(get(), search, category) }

    viewModel { (purchaseDataFactory: PurchaseDataFactory) -> PurchaseListViewModel(purchaseDataFactory, get()) }

    // rent list
    factory { (search: String, category: String) -> RentDataFactory(get(), search, category) }

    viewModel { (rentDataFactory: RentDataFactory) -> RentListViewModel(rentDataFactory, get()) }

    // interest
    factory { GetInterestPurchaseUseCase(get()) }

    factory { GetInterestRentUseCase(get()) }

    viewModel { InterestViewModel(get(), get(), get()) }

    // my post
    factory { GetMyPurchaseUseCase(get()) }

    factory { GetMyRentUseCase(get()) }

    factory { CompletePurchaseUseCase(get()) }

    factory { CompleteRentUseCase(get()) }

    viewModel { MyPostViewModel(get(), get(), get(), get(), get()) }

    // recent
    factory { GetRecentPurchaseUseCase(get()) }

    factory { GetRecentRentUseCase(get()) }

    viewModel { RecentViewModel(get(), get(), get()) }
}