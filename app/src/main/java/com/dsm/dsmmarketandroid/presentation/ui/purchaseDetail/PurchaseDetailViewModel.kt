package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.model.PurchaseDetailModel
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
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
    private val joinRoomUseCase: JoinRoomUseCase,
    private val purchaseDetailModelMapper: PurchaseDetailModelMapper,
    private val recommendModelMapper: RecommendModelMapper
) : BaseViewModel() {

    val purchaseDetail = MutableLiveData<PurchaseDetailModel>()
    val isInterest = MutableLiveData<Boolean>()

    val recommendList = MutableLiveData<List<RecommendModel>>()
    val relatedList = MutableLiveData<List<RecommendModel>>()

    val toastEvent = SingleLiveEvent<Int>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val intentChatActivityEvent = SingleLiveEvent<Bundle>()

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
                    if (it is HttpException && it.code() == 410) {
                        toastEvent.value = R.string.fail_non_exist_post
                        finishActivityEvent.call()
                    } else toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun onClickInterest(postId: Int) {
        if (isInterest.value!!) {
            addDisposable(
                unInterestUseCase.create(UnInterestUseCase.Params(postId, ProductType.PURCHASE))
                    .subscribe({
                        isInterest.value = false
                        toastEvent.value = R.string.un_interest
                    }, {
                        toastEvent.value = R.string.fail_server_error
                    })
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, ProductType.PURCHASE))
                    .subscribe({
                        isInterest.value = true
                        toastEvent.value = R.string.interest
                    }, {
                        toastEvent.value = R.string.fail_server_error
                    })
            )
        }
    }

    fun getRecommendProduct(postId: Int) {
        addDisposable(
            getRecommendUseCase.create(postId)
                .map(recommendModelMapper::mapFrom)
                .subscribe({
                    recommendList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun getRelatedProduct(postId: Int) {
        addDisposable(
            getRelatedUseCase.create(GetRelatedUseCase.Params(postId, ProductType.PURCHASE))
                .map(recommendModelMapper::mapFrom)
                .subscribe({
                    relatedList.value = it
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun createRoom(postId: Int) {
        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, 0))
                .map { roomId ->
                    joinRoomUseCase.create(roomId)
                        .subscribe({ email ->
                            intentChatActivityEvent.value = Bundle().apply {
                                putString("email", email)
                                putInt("roomId", roomId)
                            }
                        }, {
                            toastEvent.value = R.string.fail_server_error
                        })
                }.subscribe({
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}