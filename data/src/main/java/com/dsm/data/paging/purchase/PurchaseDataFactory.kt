package com.dsm.data.paging.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.PurchaseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


class PurchaseDataFactory(private val search: String = "", private val category: String = "") : DataSource.Factory<Int, Product>(), KoinComponent {

    private val purchaseRepository: PurchaseRepository by inject()

    val mutableLiveData = MutableLiveData<PurchaseKeyedDataSource>()

    override fun create(): DataSource<Int, Product> {
        val dataSource = PurchaseKeyedDataSource(purchaseRepository, search, category)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}