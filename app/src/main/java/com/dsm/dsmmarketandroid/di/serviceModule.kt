package com.dsm.dsmmarketandroid.di

import com.dsm.domain.entity.account.AccountService
import com.dsm.domain.entity.account.AccountServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    factory<AccountService> { AccountServiceImpl(get()) }
}