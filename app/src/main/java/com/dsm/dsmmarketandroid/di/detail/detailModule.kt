package com.dsm.dsmmarketandroid.di.detail

import com.dsm.data.dataSource.detail.DetailDataSource
import com.dsm.data.dataSource.detail.DetailDataSourceImpl
import com.dsm.data.mapper.PurchaseDetailMapper
import com.dsm.data.mapper.RecommendMapper
import com.dsm.data.mapper.RentDetailMapper
import com.dsm.data.repository.DetailRepositoryImpl
import com.dsm.domain.repository.DetailRepository
import com.dsm.domain.service.DetailService
import com.dsm.domain.service.DetailServiceImpl
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail.RentDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {

    factory<DetailDataSource> { DetailDataSourceImpl(get(), get(), get()) }

    factory { PurchaseDetailMapper() }

    factory { RentDetailMapper() }

    factory { RecommendMapper() }

    factory<DetailRepository> { DetailRepositoryImpl(get(), get(), get(), get()) }

    factory<DetailService> { DetailServiceImpl(get(), get()) }

    factory { InterestUseCase(get()) }

    factory { UnInterestUseCase(get()) }

    factory { GetRelatedUseCase(get()) }

    factory { GetRecommendUseCase(get()) }

    factory { RecommendModelMapper() }

    // purchase detail
    factory { GetPurchaseDetailUseCase(get()) }

    factory { PurchaseDetailModelMapper() }

    viewModel { PurchaseDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }

    // rent detail
    factory { GetRentDetailUseCase(get()) }

    factory { RentDetailModelMapper() }

    viewModel { RentDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

}