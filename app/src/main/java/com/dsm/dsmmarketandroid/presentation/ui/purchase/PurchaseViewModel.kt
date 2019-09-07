package com.dsm.dsmmarketandroid.presentation.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import java.util.concurrent.Executors

class PurchaseViewModel(
    purchaseDataFactory: PurchaseDataFactory,
    productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val purchaseListItems: LiveData<PagedList<ProductModel>>

    init {
        val executor = Executors.newFixedThreadPool(5)
        networkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }
        val productModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        purchaseListItems = LivePagedListBuilder(productModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()

    }
}