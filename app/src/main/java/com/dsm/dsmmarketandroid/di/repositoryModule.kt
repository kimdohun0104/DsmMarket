package com.dsm.dsmmarketandroid.di

import com.dsm.data.repository.AccountRepositoryImpl
import com.dsm.data.repository.PasswordRepositoryImpl
import com.dsm.data.repository.PostRepositoryImpl
import com.dsm.data.repository.PurchaseRepositoryImpl
import com.dsm.data.repository.RentRepositoryImpl
import com.dsm.domain.repository.AccountRepository
import com.dsm.domain.repository.PasswordRepository
import com.dsm.domain.repository.PostRepository
import com.dsm.domain.repository.PurchaseRepository
import com.dsm.domain.repository.RentRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    factory<PasswordRepository> { PasswordRepositoryImpl(get()) }

    factory<PostRepository> { PostRepositoryImpl(get()) }

    factory<PurchaseRepository> { PurchaseRepositoryImpl(get()) }

    factory<RentRepository> { RentRepositoryImpl(get()) }
}