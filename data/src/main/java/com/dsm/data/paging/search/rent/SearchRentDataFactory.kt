package com.dsm.data.paging.search.rent

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.SearchRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchRentDataFactory(private val search: String): DataSource.Factory<Int, Product>(), KoinComponent {

    private val searchRepository: SearchRepository by inject()

    val mutableLiveData = MutableLiveData<SearchRentKeyedDataSource>()

    override fun create(): DataSource<Int, Product> {
        val dataSource = SearchRentKeyedDataSource(searchRepository, search)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }

}