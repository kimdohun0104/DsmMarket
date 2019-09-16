package com.dsm.dsmmarketandroid.presentation.ui.recent

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetRecentPurchaseUseCase
import com.dsm.domain.usecase.GetRecentRentUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RecentMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class RecentViewModel(
    private val getRecentPurchaseUseCase: GetRecentPurchaseUseCase,
    private val getRecentRentUseCase: GetRecentRentUseCase,
    private val recentMapper: RecentMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    val intentPurchaseDetail = MutableLiveData<Int>()
    val intentRentDetail = MutableLiveData<Int>()

    fun getRecentProduct() {
        addDisposable(
            getRecentPurchaseUseCase.create(Unit)
                .flatMap {
                    purchaseList.value = recentMapper.mapFrom(it)
                    getRecentRentUseCase.create(Unit)
                }.subscribe({
                    rentList.value = recentMapper.mapFrom(it)
                }, {
                })
        )
    }
}