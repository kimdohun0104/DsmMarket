package com.dsm.dsmmarketandroid.presentation.ui.category

import androidx.lifecycle.LiveData
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

class CategoryListViewModel(private val productModelMapper: ProductModelMapper) : BaseViewModel() {

    lateinit var purchaseNetworkState: LiveData<NetworkState>
    lateinit var purchaseList: LiveData<PagedList<ProductModel>>

    lateinit var rentNetworkState: LiveData<NetworkState>
    lateinit var rentList: LiveData<PagedList<ProductModel>>

    private val executor = Executors.newFixedThreadPool(5)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(15)
        .build()

    fun purchaseInit(category: String) {
        val purchaseDataFactory = PurchaseDataFactory(category = category)
        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)
        purchaseNetworkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }

        purchaseList = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun rentInit(category: String) {
        val rentDataFactory = RentDataFactory(category = category)
        val rentModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)
        rentNetworkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }

        rentList = LivePagedListBuilder(rentModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }
}