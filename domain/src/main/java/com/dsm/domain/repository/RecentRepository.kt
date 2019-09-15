package com.dsm.domain.repository

import com.dsm.domain.entity.Recent
import io.reactivex.Flowable

interface RecentRepository {

    fun getRecentPurchase(): Flowable<List<Recent>>

    fun getRecentRent(): Flowable<List<Recent>>
}