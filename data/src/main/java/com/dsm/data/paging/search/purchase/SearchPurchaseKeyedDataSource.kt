package com.dsm.data.paging.search.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.data.paging.NetworkState
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.SearchRepository
import io.reactivex.disposables.CompositeDisposable

class SearchPurchaseKeyedDataSource(
    private val searchRepository: SearchRepository,
    private val search: String
) : PageKeyedDataSource<Int, Product>() {

    val networkState = MutableLiveData<NetworkState>()

    private val composite = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            searchRepository.searchPurchase(0, params.requestedLoadSize, search)
                .subscribe({
                    callback.onResult(it, null, 1)
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            searchRepository.searchPurchase(params.key, params.requestedLoadSize, search)
                .subscribe({
                    callback.onResult(it, params.key + 1)
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
    }
}