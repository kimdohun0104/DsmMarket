package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dsm.data.error.exception.GoneException
import com.dsm.data.error.exception.UnauthorizedException
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
                    if (it.isLocal) snackbarRetry.call()

                    rentDetailModelMapper.mapFrom(it.data).let { detail ->
                        isInterest.value = detail.isInterest
                        isMe.value = detail.isMe
                        rentDetail.value = detail
                    }
                }, {
                    toastEvent.value = when (it) {
                        is GoneException -> {
                            finishActivityEvent.call()
                            R.string.fail_non_exist_post
                        }
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    fun onClickInterest(postId: Int) {
        if (isInterest.value!!) {
            addDisposable(
                unInterestUseCase.create(UnInterestUseCase.Params(postId, ProductType.RENT))
                    .subscribe({
                        isInterest.value = false
                        toastEvent.value = R.string.un_interest
                    }, {
                        toastEvent.value = when (it) {
                            is UnauthorizedException -> R.string.fail_unauthorized
                            is GoneException -> R.string.fail_non_exist_post
                            else -> R.string.fail_server_error
                        }
                    })
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, ProductType.RENT))
                    .doOnNext { interestLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                    .subscribe({
                        isInterest.value = true
                        toastEvent.value = R.string.interest
                    }, {
                        toastEvent.value = when (it) {
                            is UnauthorizedException -> R.string.fail_unauthorized
                            is GoneException -> R.string.fail_non_exist_post
                            else -> R.string.fail_server_error
                        }
                    })
            )
        }
    }

    fun getRelatedProduct(postId: Int) {
        addDisposable(
            getRelatedUseCase.create(GetRelatedUseCase.Params(postId, ProductType.RENT))
                .map(recommendModelMapper::mapFrom)
                .subscribe({
                    relatedList.value = it
                }, {})
        )
    }

    fun createRoom(postId: Int) {
        val bundle = Bundle()

        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, ProductType.RENT))
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .doOnNext { createChatRoomLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                .flatMap { roomId ->
                    bundle.putInt("roomId", roomId)
                    joinRoomUseCase.create(roomId)
                }
                .subscribe({email ->
                    intentChatActivityEvent.value = Bundle().apply {
                        putString("email", email)
                        putString("roomTitle", rentDetail.value?.title)
                    }
                }, {
                    toastEvent.value = when (it) {
                        is UnauthorizedException -> R.string.fail_unauthorized
                        is GoneException -> R.string.fail_join_chat_room
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    fun onClickRentImage() {
        intentRentImage.value = rentDetail.value?.img
    }
}