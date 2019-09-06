package com.dsm.data.dataSource.rent

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

class RentDataSourceImpl(private val api: Api) : RentDataSource {
    override fun getRemoteRentList(page: Int, pageSize: Int): Flowable<ProductListEntity> =
        api.getRentList(page, pageSize).addSchedulers()

}