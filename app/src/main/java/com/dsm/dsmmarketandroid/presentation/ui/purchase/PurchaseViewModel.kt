package com.dsm.dsmmarketandroid.presentation.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import java.util.concurrent.Executors

class PurchaseViewModel(
    purchaseDataFactory: PurchaseDataFactory,
    purchaseModelMapper: PurchaseModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val productLiveData: LiveData<PagedList<ProductModel>>

    init {
        val executor = Executors.newFixedThreadPool(5)
        networkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }
        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(purchaseModelMapper::mapFrom)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        productLiveData = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()

    }
}