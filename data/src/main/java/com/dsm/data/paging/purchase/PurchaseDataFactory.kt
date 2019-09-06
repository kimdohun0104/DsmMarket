package com.dsm.data.paging.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Purchase


class PurchaseDataFactory(private val dataSource: PurchaseKeyedDataSource) : DataSource.Factory<Int, Purchase>() {

    val mutableLiveData = MutableLiveData<PurchaseKeyedDataSource>()

    override fun create(): DataSource<Int, Purchase> {
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}