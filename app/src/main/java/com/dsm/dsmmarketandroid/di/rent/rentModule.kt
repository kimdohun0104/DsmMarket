package com.dsm.dsmmarketandroid.di.rent

import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.dataSource.rent.RentDataSourceImpl
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.data.repository.RentRepositoryImpl
import com.dsm.domain.repository.RentRepository
import com.dsm.domain.service.RentService
import com.dsm.domain.service.RentServiceImpl
import com.dsm.domain.usecase.GetInterestRentUseCase
import org.koin.dsl.module

val rentModule = module {

    factory<RentDataSource> { RentDataSourceImpl(get(), get(), get()) }

    factory<RentRepository> { RentRepositoryImpl(get(), get(), get()) }

    factory<RentService> { RentServiceImpl(get()) }

    factory { (search: String, category: String) -> RentDataFactory(get(), search, category) }

    // interest
    factory { GetInterestRentUseCase(get()) }
}