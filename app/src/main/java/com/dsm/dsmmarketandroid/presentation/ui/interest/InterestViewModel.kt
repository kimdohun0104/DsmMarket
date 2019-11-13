package com.dsm.dsmmarketandroid.presentation.ui.interest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.GetInterestUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isPurchase

class InterestViewModel(
    private val getInterestUseCase: GetInterestUseCase,
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

    fun getInterest(type: Int) {
        addDisposable(
            getInterestUseCase.create(type)
                .doOnTerminate {
                    if (type.isPurchase()) {
                        isPurchaseRefreshing.value = false
                        isPurchaseProgressVisible.value = false
                    }
                    else {
                        isRentRefreshing.value = false
                        isRentProgressVisible.value = false
                    }
                }
                .map(productModelMapper::mapFrom)
                .subscribe({
                    if (type.isPurchase())
                        purchaseList.value = it
                    else
                        rentList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}