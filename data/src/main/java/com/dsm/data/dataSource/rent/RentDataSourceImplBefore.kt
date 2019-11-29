package com.dsm.data.dataSource.rent

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.SearchDao
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.RentImageEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.Response

class RentDataSourceImplBefore(
    private val api: Api,
    private val searchDao: SearchDao
) : RentDataSourceBefore {
    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize, search, category).addSchedulers()

    override fun modifyRent(params: Any): Flowable<Response<Unit>> =
        api.modifyRent(params).addSchedulers()

    override fun addSearchHistory(search: String): Completable =
        searchDao.addSearchHistory(SearchHistoryRoomEntity(search)).addSchedulers()

    override fun getRentImage(postId: Int): Flowable<RentImageEntity> =
        api.getRentImage(postId).addSchedulers()
}