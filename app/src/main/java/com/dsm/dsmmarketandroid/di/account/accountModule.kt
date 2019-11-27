package com.dsm.dsmmarketandroid.di.account

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.dataSource.account.AccountDataSourceImpl
import com.dsm.data.repository.AccountRepositoryImpl
import com.dsm.domain.repository.AccountRepository
import com.dsm.domain.service.AccountService
import com.dsm.domain.service.AccountServiceImpl
import org.koin.dsl.module

val accountModule = module {

    factory<AccountDataSource> { AccountDataSourceImpl(get()) }

    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    factory<AccountService> { AccountServiceImpl(get(), get()) }
}