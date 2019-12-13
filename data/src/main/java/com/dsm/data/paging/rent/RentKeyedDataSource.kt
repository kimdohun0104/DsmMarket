package com.dsm.data.paging.rent

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.data.paging.NetworkState
import com.dsm.domain.entity.Product
import com.dsm.domain.service.ProductService
import io.reactivex.disposables.CompositeDisposable

class RentKeyedDataSource(
    private val productService: ProductService,
    private val search: String,
    private val category: String
) : PageKeyedDataSource<Int, Product>() {

    val networkState = MutableLiveData<NetworkState>()

    private val composite = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            productService.getRentList(0, params.requestedLoadSize, search, category)
                .subscribe({
                    callback.onResult(it.data, null, 1)
                    networkState.postValue(
                        if (it.isLocal) NetworkState.LOCAL
                        else NetworkState.LOADED
                    )
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            productService.getRentList(params.key, params.requestedLoadSize, search, category)
                .subscribe({
                    callback.onResult(it.data, params.key + 1)
                    networkState.postValue(
                        if (it.isLocal) NetworkState.LOCAL
                        else NetworkState.LOADED
                    )
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
    }

}