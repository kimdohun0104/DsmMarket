package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.InterestUseCase
import com.dsm.domain.usecase.UnInterestUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.model.PurchaseDetailModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class PurchaseDetailViewModel(
    private val getPurchaseDetailUseCase: GetPurchaseDetailUseCase,
    private val interestUseCase: InterestUseCase,
    private val unInterestUseCase: UnInterestUseCase,
    private val purchaseDetailModelMapper: PurchaseDetailModelMapper
) : BaseViewModel() {

    val purchaseDetail = MutableLiveData<PurchaseDetailModel>()
    val isInterest = MutableLiveData<Boolean>()

    val toastNonExistEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val toastInterestEvent = SingleLiveEvent<Any>()
    val toastUnInterestEvent = SingleLiveEvent<Any>()

    fun getPurchaseDetail(postId: Int) {
        addDisposable(
            getPurchaseDetailUseCase.create(postId)
                .subscribe({
                    val result = purchaseDetailModelMapper.mapFrom(it)
                    isInterest.value = result.isInterest
                    purchaseDetail.value = result
                }, {
                    if (it is HttpException) {
                        if (it.code() == 410) {
                            toastNonExistEvent.call()
                            finishActivityEvent.call()
                        }
                    } else toastServerErrorEvent.call()
                })
        )
    }

    fun onClickInterest(postId: Int) {
        if (isInterest.value!!) {
            addDisposable(
                unInterestUseCase.create(UnInterestUseCase.Params(postId, 0))
                    .subscribe({
                        isInterest.value = false
                        toastUnInterestEvent.call()
                    }, {
                        toastServerErrorEvent.call()
                    })
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, 0))
                    .subscribe({
                        isInterest.value = true
                        toastInterestEvent.call()
                    }, {
                        toastServerErrorEvent.call()
                    })
            )
        }
    }
}