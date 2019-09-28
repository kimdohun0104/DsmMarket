package com.dsm.data.dataSource.rent

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.RentDao
import com.dsm.data.local.db.dao.SearchDao
import com.dsm.data.local.db.entity.RentDetailRoomEntity
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.RentDetailEntity
import com.dsm.data.remote.entity.RentImageEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

class RentDataSourceImpl(
    private val api: Api,
    private val rentDao: RentDao,
    private val searchDao: SearchDao
) : RentDataSource {
    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize, search, category).addSchedulers()

    override fun getRemoteRentDetail(postId: Int): Flowable<Response<RentDetailEntity>> =
        api.getRentDetail(postId, 1).addSchedulers()

    override fun getLocalRentDetail(postId: Int): RentDetailRoomEntity =
        rentDao.getRentDetail(postId)

    override fun addLocalRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable =
        rentDao.addRentDetail(rentDetailRoomEntity).addSchedulers()

    override fun modifyRent(params: Any): Flowable<Response<Unit>> =
        api.modifyRent(params).addSchedulers()

    override fun addSearchHistory(search: String): Completable =
        searchDao.addSearchHistory(SearchHistoryRoomEntity(search)).addSchedulers()

    override fun getRentImage(postId: Int): Flowable<RentImageEntity> =
        api.getRentImage(postId).addSchedulers()
}