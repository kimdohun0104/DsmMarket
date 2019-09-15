package com.dsm.data.dataSource.recent

import com.dsm.data.local.db.entity.RecentPurchaseRoomEntity
import com.dsm.data.local.db.entity.RecentRentRoomEntity
import io.reactivex.Flowable

interface RecentDataSource {

    fun getRecentPurchase(): Flowable<List<RecentPurchaseRoomEntity>>

    fun getRecentRent(): Flowable<List<RecentRentRoomEntity>>
}