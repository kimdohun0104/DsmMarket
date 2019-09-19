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

    factory<InterestRepository> { InterestRepositoryImpl(get()) }

    factory<RecentRepository> { RecentRepositoryImpl(get()) }

    factory<SearchRepository> { SearchRepositoryImpl(get()) }

    factory<MyPostRepository> { MyPostRepositoryImpl(get()) }

    factory<ReportRepository> { ReportRepositoryImpl(get()) }

    factory<RecommendRepository> { RecommendRepositoryImpl(get()) }
}