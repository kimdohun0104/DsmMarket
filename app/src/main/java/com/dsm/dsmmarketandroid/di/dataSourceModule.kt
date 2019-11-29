package com.dsm.dsmmarketandroid.di

import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.dataSource.comment.CommentDataSourceImpl
import com.dsm.data.dataSource.interest.InterestDataSource
import com.dsm.data.dataSource.interest.InterestDataSourceImpl
import com.dsm.data.dataSource.myPost.MyPostDataSource
import com.dsm.data.dataSource.myPost.MyPostDataSourceImpl
import com.dsm.data.dataSource.post.PostDataSource
import com.dsm.data.dataSource.post.PostDataSourceImpl
import com.dsm.data.dataSource.purchaseBefore.PurchaseDataSourceBefore
import com.dsm.data.dataSource.purchaseBefore.PurchaseDataSourceImplBefore
import com.dsm.data.dataSource.recent.RecentDataSource
import com.dsm.data.dataSource.recent.RecentDataSourceImpl
import com.dsm.data.dataSource.rent.RentDataSourceBefore
import com.dsm.data.dataSource.rent.RentDataSourceImplBefore
import com.dsm.data.dataSource.report.ReportDataSource
import com.dsm.data.dataSource.report.ReportDataSourceImpl
import com.dsm.data.dataSource.search.SearchDataSource
import com.dsm.data.dataSource.search.SearchDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<PostDataSource> { PostDataSourceImpl(get()) }

    factory<PurchaseDataSourceBefore> { PurchaseDataSourceImplBefore(get(), get()) }

    factory<RentDataSourceBefore> { RentDataSourceImplBefore(get(), get()) }

    factory<CommentDataSource> { CommentDataSourceImpl(get()) }

    factory<InterestDataSource> { InterestDataSourceImpl(get()) }

    factory<RecentDataSource> { RecentDataSourceImpl(get(), get()) }

    factory<SearchDataSource> { SearchDataSourceImpl(get()) }

    factory<MyPostDataSource> { MyPostDataSourceImpl(get()) }

    factory<ReportDataSource> { ReportDataSourceImpl(get()) }
}