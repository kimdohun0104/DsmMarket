package com.dsm.data.repository

import com.dsm.data.dataSource.recent.RecentDataSource
import com.dsm.data.mapper.RecentMapper
import com.dsm.domain.entity.Recent
import com.dsm.domain.repository.RecentRepository
import io.reactivex.Flowable

class RecentRepositoryImpl(private val recentDataSource: RecentDataSource) : RecentRepository {

    private val recentMapper = RecentMapper()

    override fun getRecentPurchase(): Flowable<List<Recent>> =
        recentDataSource.getRecentPurchase().map(recentMapper::mapFrom)

    override fun getRecentRent(): Flowable<List<Recent>> =
        recentDataSource.getRecentRent().map(recentMapper::mapFrom)

}