package com.dsm.dsmmarketandroid.presentation.ui.purchaseImage

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetPurchaseImageUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class PurchaseImageViewModel(private val getPurchaseImageUseCase: GetPurchaseImageUseCase) : BaseViewModel() {

    val imageList = MutableLiveData<List<String>>()

    fun getPurchaseImage(postId: Int) {
        addDisposable(
            getPurchaseImageUseCase.create(postId)
                .subscribe({
                    imageList.value = it
                }, {

                })
        )
    }
}