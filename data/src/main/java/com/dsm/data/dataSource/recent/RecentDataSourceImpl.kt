package com.dsm.data.dataSource.recent

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.dao.RentDao
import com.dsm.data.local.db.entity.RecentRoomEntity
import io.reactivex.Flowable

class RecentDataSourceImpl(
    private val purchaseDao: PurchaseDao,
    private val rentDao: RentDao
) : RecentDataSource {

    override fun getRecentPurchase(): Flowable<List<RecentRoomEntity>> =
         purchaseDao.getRecentPurchase().addSchedulers()

    override fun getRecentRent(): Flowable<List<RecentRoomEntity>> =
        rentDao.getRecentRent().addSchedulers()

}