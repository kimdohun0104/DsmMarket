package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.dataSource.account.AccountDataSourceImpl
import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.data.dataSource.password.PasswordDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<AccountDataSource> { AccountDataSourceImpl(get()) }

    factory<PasswordDataSource> { PasswordDataSourceImpl(get()) }
}