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
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val search = MutableLiveData<String>()

    lateinit var purchaseNetworkState: LiveData<NetworkState>
    lateinit var purchaseListItems: LiveData<PagedList<ProductModel>>

    lateinit var rentNetworkState: LiveData<NetworkState>
    lateinit var rentListItems: LiveData<PagedList<ProductModel>>

    val intentSearchResult = MutableLiveData<String>()

    val isSearchEnable = Transformations.map(search) { it != "" }

    private val executor = Executors.newFixedThreadPool(5)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(15)
        .build()

    fun purchaseInit(search: String) {
        val purchaseDataFactory = PurchaseDataFactory(search = search)
        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)
        purchaseNetworkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }

        purchaseListItems = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun rentInit(search: String) {
        val rentDataFactory = RentDataFactory(search = search)
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