package com.dsm.dsmmarketandroid.presentation.ui.rent

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import java.util.concurrent.Executors

class RentViewModel(
    rentDataFactory: RentDataFactory,
    productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val rentLiveData: LiveData<PagedList<ProductModel>>

    init {
        val executor = Executors.newFixedThreadPool(5)
        networkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }
        val productModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        rentLiveData = LivePagedListBuilder(productModelDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }
}