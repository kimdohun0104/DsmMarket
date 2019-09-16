package com.dsm.data.dataSource.interest

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable
import retrofit2.Response

class InterestDataSourceImpl(private val api: Api) : InterestDataSource {
    override fun interest(postId: Int, type: Int): Flowable<Response<Unit>> =
        api.interest(postId, type).addSchedulers()

    override fun unInterest(postId: Int, type: Int): Flowable<Response<Unit>> =
        api.unInterest(postId, type).addSchedulers()

    override fun getInterest(type: Int): Flowable<ProductListEntity> =
        api.getInterest(type).addSchedulers()
}