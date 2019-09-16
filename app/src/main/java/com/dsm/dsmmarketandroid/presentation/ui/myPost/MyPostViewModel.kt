package com.dsm.dsmmarketandroid.presentation.ui.myPost

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetMyPurchaseUseCase
import com.dsm.domain.usecase.GetMyRentUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class MyPostViewModel(
    private val getMyPurchaseUseCase: GetMyPurchaseUseCase,
    private val getMyRentUseCase: GetMyRentUseCase,
    private val productModelMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val intentPurchaseDetail = MutableLiveData<Int>()
    val intentRentDetail = MutableLiveData<Int>()

    val intentPurchaseModify = MutableLiveData<Int>()
    val intentRentModify = MutableLiveData<Int>()

    fun getMyPurchase() {
        addDisposable(
            getMyPurchaseUseCase.create(Unit)
                .subscribe({
                    purchaseList.value = productModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun getMyRent() {
        addDisposable(
            getMyRentUseCase.create(Unit)
                .subscribe({
                    rentList.value = productModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}