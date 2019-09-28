package com.dsm.data.repository

import com.dsm.data.dataSource.recent.RecentDataSource
import com.dsm.data.mapper.RecentMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RecentRepository
import io.reactivex.Flowable

class RecentRepositoryImpl(private val recentDataSource: RecentDataSource) : RecentRepository {

    private val recentMapper = RecentMapper()

    override fun getRecentPurchase(): Flowable<List<Product>> =
        recentDataSource.getRecentPurchase().map(recentMapper::mapFrom)

    override fun getRecentRent(): Flowable<List<Product>> =
        recentDataSource.getRecentRent().map(recentMapper::mapFromRent)

}