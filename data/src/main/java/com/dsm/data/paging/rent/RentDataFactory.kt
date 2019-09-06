package com.dsm.data.paging.rent

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Product

class RentDataFactory(private val dataSource: RentKeyedDataSource) : DataSource.Factory<Int, Product>() {

    val mutableLiveData = MutableLiveData<RentKeyedDataSource>()

    override fun create(): DataSource<Int, Product> {
        mutableLiveData.postValue(dataSource)
        return dataSource
    }

}