package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.AccountDataSource
import com.dsm.data.dataSource.AccountDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<AccountDataSource> { AccountDataSourceImpl(get(), get()) }
}