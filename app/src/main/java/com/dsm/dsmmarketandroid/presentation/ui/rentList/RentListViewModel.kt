package com.dsm.dsmmarketandroid.presentation.ui.rentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    val rentListItems: LiveData<PagedList<ProductModel>>

    val intentRentDetail = MutableLiveData<Int>()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20)
            .setPageSize(15)
            .build()

        val rentModelDataFactory = rentDataFactory.mapByPage(productModelMapper::mapFrom)
        networkState = Transformations.switchMap(rentDataFactory.mutableLiveData) { it.networkState }

        rentListItems = LivePagedListBuilder(rentModelDataFactory, pagedListConfig).build()
    }

    fun refreshList() {
        rentListItems.value?.dataSource?.invalidate()
    }
}