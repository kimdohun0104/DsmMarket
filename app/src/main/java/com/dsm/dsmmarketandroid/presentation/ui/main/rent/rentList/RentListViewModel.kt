package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dsm.data.paging.NetworkState
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class RentListViewModel(
    rentDataFactory: RentDataFactory,
    productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val networkState: LiveData<NetworkState>
    val rentItems: LiveData<PagedList<ProductModel>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        val rentModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)
        networkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }

        rentItems = LivePagedListBuilder(rentModelDataFactory, pagedListConfig).build()
    }

    fun refreshList() {
        rentItems.value?.dataSource?.invalidate()
    }
}