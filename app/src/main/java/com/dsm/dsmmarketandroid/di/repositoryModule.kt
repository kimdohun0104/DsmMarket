package com.dsm.dsmmarketandroid.di

import com.dsm.data.repository.*
import com.dsm.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    factory<PasswordRepository> { PasswordRepositoryImpl(get()) }

    factory<PostRepository> { PostRepositoryImpl(get()) }

    factory<PurchaseRepository> { PurchaseRepositoryImpl(get()) }

    factory<RentRepository> { RentRepositoryImpl(get()) }

    factory<CommentRepository> { CommentRepositoryImpl(get()) }
}