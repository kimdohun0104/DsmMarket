package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.rent.RentDataFactory
import org.koin.dsl.module

val pagingModule = module {
    factory { (search: String, category: String) -> RentDataFactory(get(), search, category) }
}