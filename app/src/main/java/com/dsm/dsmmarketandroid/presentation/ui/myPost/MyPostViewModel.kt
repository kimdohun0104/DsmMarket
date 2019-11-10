package com.dsm.dsmmarketandroid.presentation.ui.myPost

import androidx.lifecycle.LiveData
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
import com.dsm.dsmmarketandroid.presentation.util.isPurchase
import io.reactivex.Flowable

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

    val deletePositionFromPurchase = SingleLiveEvent<Int>()
    val deletePositionFromRent = SingleLiveEvent<Int>()

    val isPurchaseProgressVisible = MutableLiveData<Boolean>().apply { value = true }
    val isRentProgressVisible = MutableLiveData<Boolean>().apply { value = true }

    val isPurchaseEmpty: LiveData<Boolean> = Transformations.map(purchaseList) { it.isEmpty() }
    val isRentEmpty: LiveData<Boolean> = Transformations.map(rentList) { it.isEmpty() }

    val isPurchaseRefreshing = MutableLiveData<Boolean>()
    val isRentRefreshing = MutableLiveData<Boolean>()

    fun getMyPost(type: Int) {
        addDisposable(
            Flowable.just(type)
                .doOnTerminate {
                    if (type.isPurchase()) {
                        isPurchaseRefreshing.value = false
                        isPurchaseProgressVisible.value = false
                    } else {
                        isRentRefreshing.value = false
                        isRentProgressVisible.value = false
                    }
                }.flatMap {
                    if (it.isPurchase()) getMyPurchaseUseCase.create(Unit)
                    else getMyRentUseCase.create(Unit)
                }.map(productModelMapper::mapFrom)
                .subscribe({
                    if (type.isPurchase()) purchaseList.value = it
                    else rentList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun completePost(position: Int, type: Int) {
        addDisposable(
            Flowable.just(type)
                .flatMap {
                    if (type.isPurchase()) completePurchaseUseCase.create(purchaseList.value?.get(position)?.postId ?: -1)
                    else completeRentUseCase.create(rentList.value?.get(position)?.postId ?: -1)
                }.subscribe({
                    if (type.isPurchase()) deletePositionFromPurchase.value = position
                    else deletePositionFromRent.value = position

                    dismissEvent.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}