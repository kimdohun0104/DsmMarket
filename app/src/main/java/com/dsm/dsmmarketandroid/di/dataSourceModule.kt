package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.dataSource.account.AccountDataSourceImpl
import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.dataSource.comment.CommentDataSourceImpl
import com.dsm.data.dataSource.interest.InterestDataSource
import com.dsm.data.dataSource.interest.InterestDataSourceImpl
import com.dsm.data.dataSource.myPost.MyPostDataSource
import com.dsm.data.dataSource.myPost.MyPostDataSourceImpl
import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.data.dataSource.password.PasswordDataSourceImpl
import com.dsm.data.dataSource.post.PostDataSource
import com.dsm.data.dataSource.post.PostDataSourceImpl
import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.dataSource.purchase.PurchaseDataSourceImpl
import com.dsm.data.dataSource.recent.RecentDataSource
import com.dsm.data.dataSource.recent.RecentDataSourceImpl
import com.dsm.data.dataSource.recommend.RecommendDataSource
import com.dsm.data.dataSource.recommend.RecommendDataSourceImpl
import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.dataSource.rent.RentDataSourceImpl
import com.dsm.data.dataSource.report.ReportDataSource
import com.dsm.data.dataSource.report.ReportDataSourceImpl
import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.dataSource.search.SearchDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<AccountDataSource> { AccountDataSourceImpl(get()) }

    factory<PasswordDataSource> { PasswordDataSourceImpl(get()) }

    factory<PostDataSource> { PostDataSourceImpl(get()) }

    factory<PurchaseDataSource> { PurchaseDataSourceImpl(get(), get(), get()) }

    factory<RentDataSource> { RentDataSourceImpl(get(), get(), get()) }

    factory<CommentDataSource> { CommentDataSourceImpl(get()) }

    factory<InterestDataSource> { InterestDataSourceImpl(get()) }

    factory<RecentDataSource> { RecentDataSourceImpl(get(), get()) }

    factory<SearchDataSource> { SearchDataSourceImpl(get(), get()) }

    factory<MyPostDataSource> { MyPostDataSourceImpl(get()) }

    factory<ReportDataSource> { ReportDataSourceImpl(get()) }

    factory<RecommendDataSource> { RecommendDataSourceImpl(get()) }
}