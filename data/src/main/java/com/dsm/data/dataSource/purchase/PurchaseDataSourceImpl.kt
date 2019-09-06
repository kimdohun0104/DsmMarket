package com.dsm.data.dataSource.purchase

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PurchaseListEntity
import io.reactivex.Flowable

class PurchaseDataSourceImpl(private val api: Api) : PurchaseDataSource {

    override fun getRemotePurchaseList(page: Int, pageSize: Int): Flowable<PurchaseListEntity> =
        api.getPurchaseList(page, pageSize).addSchedulers()
}