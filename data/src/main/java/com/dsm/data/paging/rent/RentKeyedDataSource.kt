package com.dsm.data.paging.rent

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.data.paging.NetworkState
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RentRepository
import io.reactivex.disposables.CompositeDisposable

class RentKeyedDataSource(
    private val rentRepository: RentRepository,
    private val search: String,
    private val category: String
) : PageKeyedDataSource<Int, Product>() {

    val networkState = MutableLiveData<NetworkState>()

    private val composite = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            rentRepository.getRentList(0, params.requestedLoadSize, search, category)
                .subscribe({
                    callback.onResult(it, null, 1)
                    if (it.isNotEmpty()) networkState.postValue(NetworkState.LOADED)
                    else networkState.postValue(NetworkState.EMPTY)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            rentRepository.getRentList(params.key, params.requestedLoadSize, search, category)
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