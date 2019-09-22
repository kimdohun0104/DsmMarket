package com.dsm.dsmmarketandroid.presentation.ui.interest

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetInterestUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class InterestViewModel(
    private val getInterestUseCase: GetInterestUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun getInterestPurchase() {
        addDisposable(
            getInterestUseCase.create(0)
                .subscribe({
                    purchaseList.value = productModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun getInterestRent() {
        addDisposable(
            getInterestUseCase.create(1)
                .subscribe({
                    rentList.value = productModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}