package com.dsm.data.dataSource.rent

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.RentDao
import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.RentDetailEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

class RentDataSourceImpl(
    private val api: Api,
    private val rentDao: RentDao
) : RentDataSource {
    override fun getRemoteRentList(page: Int, pageSize: Int): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize).addSchedulers()

    override fun getRemoteRentDetail(postId: Int): Flowable<Response<RentDetailEntity>> =
        api.getRentDetail(postId, 1).addSchedulers()

    override fun getLocalRentDetail(postId: Int): RentDetailRoomEntity =
        rentDao.getRentDetail(postId)

    override fun addLocalRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable =
        rentDao.addRentDetail(rentDetailRoomEntity).addSchedulers()
}