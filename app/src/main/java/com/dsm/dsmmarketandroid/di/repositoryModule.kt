package com.dsm.dsmmarketandroid.di

import com.dsm.data.repository.AccountRepositoryImpl
import com.dsm.domain.repository.AccountRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AccountRepository> { AccountRepositoryImpl(get()) }
}