package com.dsm.dsmmarketandroid.presentation.ui.purchaseImage

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetPurchaseImageUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class PurchaseImageViewModel(private val getPurchaseImageUseCase: GetPurchaseImageUseCase) : BaseViewModel() {

    val imageList = MutableLiveData<List<String>>()

    val toastEvent = SingleLiveEvent<Int>()

    fun getPurchaseImage(postId: Int) {
        addDisposable(
            getPurchaseImageUseCase.create(postId)
                .subscribe({
                    imageList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}