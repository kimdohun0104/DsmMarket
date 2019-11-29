package com.dsm.dsmmarketandroid.di

import com.dsm.data.repository.*
import com.dsm.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {

    factory<PostRepository> { PostRepositoryImpl(get()) }

    factory<PurchaseRepositoryBefore> { PurchaseRepositoryImplBefore(get()) }

    factory<RentRepositoryBefore> { RentRepositoryImplBefore(get()) }

    factory<CommentRepository> { CommentRepositoryImpl(get()) }

    factory<InterestRepository> { InterestRepositoryImpl(get()) }

    factory<RecentRepository> { RecentRepositoryImpl(get()) }

    factory<SearchRepository> { SearchRepositoryImpl(get()) }

    factory<MyPostRepository> { MyPostRepositoryImpl(get()) }

    factory<ReportRepository> { ReportRepositoryImpl(get()) }
}