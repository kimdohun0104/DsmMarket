package com.dsm.dsmmarketandroid.presentation.ui.main.me.recent

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetRecentPurchaseUseCase
import com.dsm.domain.usecase.GetRecentRentUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel

class RecentViewModel(
    private val getRecentPurchaseUseCase: GetRecentPurchaseUseCase,
    private val getRecentRentUseCase: GetRecentRentUseCase,
    private val recentMapper: ProductModelMapper
) : BaseViewModel() {

    val purchaseList = MutableLiveData<List<ProductModel>>()
    val rentList = MutableLiveData<List<ProductModel>>()

    fun getRecentProduct() {
        addDisposable(
            getRecentPurchaseUseCase.create(Unit)
                .map(recentMapper::mapFrom)
                .flatMap {
                    purchaseList.value = it
                    getRecentRentUseCase.create(Unit)
                }
                .map(recentMapper::mapFrom)
                .subscribe({
                    rentList.value = it
                }, {
                })
        )
    }
}