package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.model.RentDetailModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class RentDetailViewModel(
    private val getRentDetailUseCase: GetRentDetailUseCase,
    private val interestUseCase: InterestUseCase,
    private val unInterestUseCase: UnInterestUseCase,
    private val getRelatedUseCase: GetRelatedUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val joinRoomUseCase: JoinRoomUseCase,
    private val recommendModelMapper: RecommendModelMapper,
    private val rentDetailModelMapper: RentDetailModelMapper
) : BaseViewModel() {

    val rentDetail = MutableLiveData<RentDetailModel>()
    val isMe = MutableLiveData<Boolean>()
    val isInterest = MutableLiveData<Boolean>()

    val relatedList = MutableLiveData<List<RecommendModel>>()

    val toastEvent = SingleLiveEvent<Int>()
    val intentChatActivityEvent = SingleLiveEvent<Bundle>()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    val interestLogEvent = SingleLiveEvent<Bundle>()
    val rentDetailLogEvent = SingleLiveEvent<Bundle>()

    val finishActivityEvent = SingleLiveEvent<Unit>()

    val createChatRoomLogEvent = SingleLiveEvent<Bundle>()

    val intentRentImage = SingleLiveEvent<String>()

    val snackbarRetry = SingleLiveEvent<Unit>()

    fun getRentDetail(postId: Int) {
        addDisposable(
            getRentDetailUseCase.create(postId)
                .delay(70, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { rentDetailLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                .subscribe({
                    when (it) {
                        is Resource.Success -> {
                            if (it.isLocal) snackbarRetry.call()
                            rentDetailModelMapper.mapFrom(it.data).let { detail ->
                                isInterest.value = detail.isInterest
                                isMe.value = detail.isMe
                                rentDetail.value = detail
                            }
                        }
                        is Resource.Error -> {
                            when (it.error) {
                                is ErrorEntity.Gone -> {
                                    toastEvent.value = R.string.fail_non_exist_post
                                    finishActivityEvent.call()
                                }
                                else -> toastEvent.value = R.string.fail_server_error
                            }
                        }
                    }
                }, {})
        )
    }

    fun onClickInterest(postId: Int) {
        if (isInterest.value!!) {
            addDisposable(
                unInterestUseCase.create(UnInterestUseCase.Params(postId, ProductType.RENT))
                    .subscribe({
                        when (it) {
                            is Resource.Success -> {
                                isInterest.value = false
                                toastEvent.value = R.string.un_interest
                            }
                            is Resource.Error -> {
                                when (it.error) {
                                    is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                    is ErrorEntity.Gone -> toastEvent.value = R.string.fail_non_exist_post
                                    else -> toastEvent.value = R.string.fail_server_error
                                }
                            }
                        }
                    }, {})
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, ProductType.RENT))
                    .doOnNext { interestLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                    .subscribe({
                        when (it) {
                            is Resource.Success -> {
                                isInterest.value = true
                                toastEvent.value = R.string.interest
                            }
                            is Resource.Error -> {
                                when (it.error) {
                                    is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                    is ErrorEntity.Gone -> toastEvent.value = R.string.fail_non_exist_post
                                    else -> toastEvent.value = R.string.fail_server_error
                                }
                            }
                        }
                    }, {})
            )
        }
    }

    fun getRelatedProduct(postId: Int) {
        addDisposable(
            getRelatedUseCase.create(GetRelatedUseCase.Params(postId, ProductType.RENT))
                .subscribe({
                    if (it is Resource.Success) relatedList.value = recommendModelMapper.mapFrom(it.data)
                }, {})
        )
    }

    fun createRoom(postId: Int) {
        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, ProductType.RENT))
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .doOnNext { createChatRoomLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                .map { roomId ->
                    joinRoomUseCase.create(roomId)
                        .subscribe({
                            when (it) {
                                is Resource.Success -> intentChatActivityEvent.value = Bundle().apply {
                                    putString("email", it.data)
                                    putInt("roomId", roomId)
                                    putString("roomTitle", rentDetail.value?.title)
                                }
                                is Resource.Error -> {
                                    when (it.error) {
                                        is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                        is ErrorEntity.Gone -> toastEvent.value = R.string.fail_join_chat_room
                                        else -> toastEvent.value = R.string.fail_server_error
                                    }
                                }
                            }
                        }, {})
                }.subscribe({
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun onClickRentImage() {
        intentRentImage.value = rentDetail.value?.img
    }
}