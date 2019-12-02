package com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseList

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class PurchaseListViewModel(
    purchaseDataFactory: PurchaseDataFactory,
    productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val purchaseItems: LiveData<PagedList<ProductModel>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)
        networkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }

        purchaseItems = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig).build()
    }

    fun refreshList() {
        purchaseItems.value?.dataSource?.invalidate()
    }
}