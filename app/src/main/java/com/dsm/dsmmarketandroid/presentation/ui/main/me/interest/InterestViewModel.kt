package com.dsm.dsmmarketandroid.presentation.ui.main.me.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.GetInterestPurchaseUseCase
import com.dsm.domain.usecase.GetInterestRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class InterestViewModel(
    private val getInterestRentUseCase: GetInterestRentUseCase,
    private val getInterestPurchaseUseCase: GetInterestPurchaseUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val isPurchaseProgressVisible = MutableLiveData<Boolean>().apply { value = true }
    val isRentProgressVisible = MutableLiveData<Boolean>().apply { value = true }

    val isPurchaseEmpty: LiveData<Boolean> = Transformations.map(purchaseList) { it.isEmpty() }
    val isRentEmpty: LiveData<Boolean> = Transformations.map(rentList) { it.isEmpty() }

    val toastEvent = SingleLiveEvent<Int>()

    val isPurchaseRefreshing = MutableLiveData<Boolean>()
    val isRentRefreshing = MutableLiveData<Boolean>()

    fun getPurchaseInterest() {
        addDisposable(
            getInterestPurchaseUseCase.create(Unit)
                .doOnTerminate {
                    isPurchaseRefreshing.value = false
                    isPurchaseProgressVisible.value = false
                }
                .map(productModelMapper::mapFrom)
                .subscribe({
                    purchaseList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun getRentInterest() {
        addDisposable(
            getInterestRentUseCase.create(Unit)
                .doOnTerminate {
                    isRentRefreshing.value = false
                    isRentProgressVisible.value = false
                }
                .map(productModelMapper::mapFrom)
                .subscribe({
                    rentList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}