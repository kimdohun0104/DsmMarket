package com.dsm.data.dataSource.interest

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

class InterestDataSourceImpl(private val api: Api) : InterestDataSource {

    override fun getInterest(type: Int): Flowable<ProductListEntity> =
        api.getInterest(type).addSchedulers()
}