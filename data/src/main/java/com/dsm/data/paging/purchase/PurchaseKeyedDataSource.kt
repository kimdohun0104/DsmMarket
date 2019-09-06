package com.dsm.data.paging.purchase

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.dsm.data.paging.NetworkState
import com.dsm.domain.entity.Purchase
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.disposables.CompositeDisposable

class PurchaseKeyedDataSource(private val purchaseRepository: PurchaseRepository) : PageKeyedDataSource<Int, Purchase>() {

    val networkState = MutableLiveData<NetworkState>()

    private val composite = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Purchase>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            purchaseRepository.getPurchaseList(0, params.requestedLoadSize)
                .subscribe({
                    callback.onResult(it, null, 1)
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Purchase>) {
        networkState.postValue(NetworkState.LOADING)

        composite.add(
            purchaseRepository.getPurchaseList(params.key, params.requestedLoadSize)
                .subscribe({
                    callback.onResult(it, params.key + 1)
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.FAILED)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Purchase>) {
    }

}