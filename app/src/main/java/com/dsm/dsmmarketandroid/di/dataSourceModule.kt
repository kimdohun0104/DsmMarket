package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.dataSource.account.AccountDataSourceImpl
import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.dataSource.comment.CommentDataSourceImpl
import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.data.dataSource.password.PasswordDataSourceImpl
import com.dsm.data.dataSource.post.PostDataSource
import com.dsm.data.dataSource.post.PostDataSourceImpl
import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.dataSource.purchase.PurchaseDataSourceImpl
import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.dataSource.rent.RentDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<AccountDataSource> { AccountDataSourceImpl(get()) }

    factory<PasswordDataSource> { PasswordDataSourceImpl(get()) }

    factory<PostDataSource> { PostDataSourceImpl(get()) }

    factory<PurchaseDataSource> { PurchaseDataSourceImpl(get(), get()) }

    factory<RentDataSource> { RentDataSourceImpl(get(), get()) }

    factory<CommentDataSource> { CommentDataSourceImpl(get()) }
}