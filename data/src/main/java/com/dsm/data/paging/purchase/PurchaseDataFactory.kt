package com.dsm.data.paging.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.PurchaseRepository


class PurchaseDataFactory(
    private val purchaseRepository: PurchaseRepository,
    private val search: String,
    private val category: String
) : DataSource.Factory<Int, Product>() {

    val mutableLiveData = MutableLiveData<PurchaseKeyedDataSource>()

    override fun create(): DataSource<Int, Product> {
        val dataSource = PurchaseKeyedDataSource(purchaseRepository, search, category)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}