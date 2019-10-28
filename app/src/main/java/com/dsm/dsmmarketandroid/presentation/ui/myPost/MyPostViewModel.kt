package com.dsm.dsmmarketandroid.presentation.ui.myPost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.CompletePurchaseUseCase
import com.dsm.domain.usecase.CompleteRentUseCase
import com.dsm.domain.usecase.GetMyPurchaseUseCase
import com.dsm.domain.usecase.GetMyRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class MyPostViewModel(
    private val getMyPurchaseUseCase: GetMyPurchaseUseCase,
    private val getMyRentUseCase: GetMyRentUseCase,
    private val completePurchaseUseCase: CompletePurchaseUseCase,
    private val completeRentUseCase: CompleteRentUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val dismissEvent = SingleLiveEvent<Any>()

    val toastEvent = SingleLiveEvent<Int>()

    val deletePositionFromPurchase = MutableLiveData<Int>()
    val deletePositionFromRent = MutableLiveData<Int>()

    val hidePurchaseLoadingEvent = SingleLiveEvent<Any>()
    val hideRentLoadingEvent = SingleLiveEvent<Any>()

    val isPurchaseEmpty = Transformations.map(purchaseList) { it.isEmpty() }
    val isRentEmpty = Transformations.map(rentList) { it.isEmpty() }

    val hidePurchaseRefresh = SingleLiveEvent<Any>()
    val hideRentRefresh = SingleLiveEvent<Any>()

    fun getMyPurchase() {
        addDisposable(
            getMyPurchaseUseCase.create(Unit)
                .map(productModelMapper::mapFrom)
                .subscribe({
                    purchaseList.value = it
                    hidePurchaseLoadingEvent.call()
                    hidePurchaseRefresh.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun getMyRent() {
        addDisposable(
            getMyRentUseCase.create(Unit)
                .map(productModelMapper::mapFrom)
                .subscribe({
                    rentList.value = it
                    hideRentLoadingEvent.call()
                    hideRentRefresh.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun completePurchase(position: Int) {
        addDisposable(
            completePurchaseUseCase.create(purchaseList.value?.get(position)?.postId ?: -1)
                .subscribe({
                    dismissEvent.call()
                    deletePositionFromPurchase.value = position
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun completeRent(position: Int) {
        addDisposable(
            completeRentUseCase.create(rentList.value?.get(position)?.postId ?: -1)
                .subscribe({
                    dismissEvent.call()
                    deletePositionFromRent.value = position
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}