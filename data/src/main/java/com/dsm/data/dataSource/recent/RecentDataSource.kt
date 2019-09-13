package com.dsm.data.dataSource.recent

import com.dsm.data.local.db.entity.RecentRoomEntity
import io.reactivex.Flowable

interface RecentDataSource {

    fun getRecentPurchase(): Flowable<List<RecentRoomEntity>>

    fun getRecentRent(): Flowable<List<RecentRoomEntity>>
}