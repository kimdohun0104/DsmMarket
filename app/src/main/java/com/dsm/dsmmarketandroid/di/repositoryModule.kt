package com.dsm.dsmmarketandroid.di

import com.dsm.data.repository.AccountRepositoryImpl
import com.dsm.data.repository.PasswordRepositoryImpl
import com.dsm.domain.repository.AccountRepository
import com.dsm.domain.repository.PasswordRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    factory<PasswordRepository> { PasswordRepositoryImpl(get()) }
}