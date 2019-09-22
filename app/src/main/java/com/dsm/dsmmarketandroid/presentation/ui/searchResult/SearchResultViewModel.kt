package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import java.util.concurrent.Executors

class SearchResultViewModel(
    purchaseDataFactory: PurchaseDataFactory,
    rentDataFactory: RentDataFactory,
    productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val search = MutableLiveData<String>()

    val purchaseNetworkState: LiveData<NetworkState>
    val purchaseListItems: LiveData<PagedList<ProductModel>>

    val rentNetworkState: LiveData<NetworkState>
    val rentListItems: LiveData<PagedList<ProductModel>>

    val intentSearchResult = MutableLiveData<String>()

    val isSearchEnable = Transformations.map(search) { it != "" }

    init {
        val executor = Executors.newFixedThreadPool(5)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)
        purchaseNetworkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }

        purchaseListItems = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()

        val rentModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)
        rentNetworkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }

        rentListItems = LivePagedListBuilder(rentModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun search() {
        intentSearchResult.value = search.value!!
    }
}