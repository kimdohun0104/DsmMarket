package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.model.PurchaseDetailModel
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

class PurchaseDetailViewModel(
    private val getPurchaseDetailUseCase: GetPurchaseDetailUseCase,
    private val interestUseCase: InterestUseCase,
    private val unInterestUseCase: UnInterestUseCase,
    private val getRecommendUseCase: GetRecommendUseCase,
    private val getRelatedUseCase: GetRelatedUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val purchaseDetailModelMapper: PurchaseDetailModelMapper,
    private val recommendModelMapper: RecommendModelMapper
) : BaseViewModel() {

    val purchaseDetail = MutableLiveData<PurchaseDetailModel>()
    val isInterest = MutableLiveData<Boolean>()

    val recommendList = MutableLiveData<List<RecommendModel>>()
    val relatedList = MutableLiveData<List<RecommendModel>>()

    val toastNonExistEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val toastInterestEvent = SingleLiveEvent<Any>()
    val toastUnInterestEvent = SingleLiveEvent<Any>()

    val startChatActivity = MutableLiveData<String>()

    fun getPurchaseDetail(postId: Int) {
        addDisposable(
            getPurchaseDetailUseCase.create(postId)
                .map(purchaseDetailModelMapper::mapFrom)
                .delay(80, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isInterest.value = it.isInterest
                    purchaseDetail.value = it
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

    fun getRecommendProduct(postId: Int) {
        addDisposable(
            getRecommendUseCase.create(postId)
                .subscribe({
                    recommendList.value = recommendModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun getRelatedProduct(postId: Int) {
        addDisposable(
            getRelatedUseCase.create(GetRelatedUseCase.Params(postId, 0))
                .subscribe({
                    relatedList.value = recommendModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun createRoom(postId: Int) {
        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, 0))
                .subscribe({
                    startChatActivity.value = it
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}