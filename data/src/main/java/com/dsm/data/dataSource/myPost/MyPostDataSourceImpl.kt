package com.dsm.data.dataSource.myPost

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable

class MyPostDataSourceImpl(private val api: Api) : MyPostDataSource {
    override fun getMyPurchase(): Flowable<ProductListEntity> =
        api.getMyPurchase().addSchedulers()

    override fun getMyRent(): Flowable<ProductListEntity> =
        api.getMyRent().addSchedulers()

}