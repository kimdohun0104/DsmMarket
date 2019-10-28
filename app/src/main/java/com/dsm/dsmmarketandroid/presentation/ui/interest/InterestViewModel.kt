package com.dsm.dsmmarketandroid.presentation.ui.interest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.GetInterestUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class InterestViewModel(
    private val getInterestUseCase: GetInterestUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val hidePurchaseProgressEvent = SingleLiveEvent<Any>()
    val hideRentProgressEvent = SingleLiveEvent<Any>()

    val toastEvent = SingleLiveEvent<Int>()

    val hidePurchaseRefresh = SingleLiveEvent<Any>()
    val hideRentRefresh = SingleLiveEvent<Any>()

    val isPurchaseEmpty = Transformations.map(purchaseList) { it.isEmpty() }
    val isRentEmpty = Transformations.map(rentList) { it.isEmpty() }

    fun getInterestPurchase() {
        addDisposable(
            getInterestUseCase.create(ProductType.PURCHASE)
                .map(productModelMapper::mapFrom)
                .subscribe({
                    purchaseList.value = it
                    hidePurchaseProgressEvent.call()
                    hidePurchaseRefresh.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun getInterestRent() {
        addDisposable(
            getInterestUseCase.create(ProductType.RENT)
                .map(productModelMapper::mapFrom)
                .subscribe({
                    rentList.value = it
                    hideRentProgressEvent.call()
                    hideRentRefresh.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}