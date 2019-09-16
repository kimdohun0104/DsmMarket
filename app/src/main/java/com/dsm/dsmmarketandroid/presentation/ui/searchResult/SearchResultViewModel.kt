package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.search.purchase.SearchPurchaseDataFactory
import com.dsm.data.paging.search.rent.SearchRentDataFactory
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.usecase.AddSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import java.util.concurrent.Executors

class SearchResultViewModel(
    private val addSearchHistoryUseCase: AddSearchHistoryUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val search = MutableLiveData<String>()

    lateinit var purchaseNetworkState: LiveData<NetworkState>
    lateinit var purchaseListItems: LiveData<PagedList<ProductModel>>

    lateinit var rentNetworkState: LiveData<NetworkState>
    lateinit var rentListItems: LiveData<PagedList<ProductModel>>

    val intentPurchaseDetail = MutableLiveData<Int>()
    val intentRentDetail = MutableLiveData<Int>()
    val intentSearchResult = MutableLiveData<String>()

    private val executor = Executors.newFixedThreadPool(5)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(20)
        .setPageSize(15)
        .build()

    fun purchaseInit(search: String) {
        val purchaseDataFactory = SearchPurchaseDataFactory(search)
        val purchaseModelDataFactory = purchaseDataFactory.mapByPage(productModelMapper::mapFrom)
        purchaseNetworkState = Transformations.switchMap(purchaseDataFactory.mutableLiveData) { it.networkState }

        purchaseListItems = LivePagedListBuilder(purchaseModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun rentInit(search: String) {
        val rentDataFactory = SearchRentDataFactory(search)
        val rentModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)
        rentNetworkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }

        rentListItems = LivePagedListBuilder(rentModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun search() {
        val search = search.value!!
        addSearchHistoryUseCase.create(SearchHistory(search)).subscribe()
        intentSearchResult.value = search
    }
}