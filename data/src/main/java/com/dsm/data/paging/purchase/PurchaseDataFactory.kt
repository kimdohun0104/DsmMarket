package com.dsm.data.paging.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Product


class PurchaseDataFactory(private val dataSource: PurchaseKeyedDataSource) : DataSource.Factory<Int, Product>() {

    val mutableLiveData = MutableLiveData<PurchaseKeyedDataSource>()

    override fun create(): DataSource<Int, Product> {
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}