package com.dsm.dsmmarketandroid.presentation.ui.rentDetail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.model.RentDetailModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.HttpException
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
    val isInterest = MutableLiveData<Boolean>()

    val relatedList = MutableLiveData<List<RecommendModel>>()

    val toastEvent = SingleLiveEvent<Int>()
    val startChatActivityEvent = SingleLiveEvent<Bundle>()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    fun getRentDetail(postId: Int) {
        addDisposable(
            getRentDetailUseCase.create(postId)
                .map(rentDetailModelMapper::mapFrom)
                .delay(70, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isInterest.value = it.isInterest
                    rentDetail.value = it
                }, {
                    if (it is HttpException && it.code() == 410)
                        toastEvent.value = R.string.fail_non_exist_post
                    else
                        toastEvent.value = R.string.fail_server_error
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
                        toastEvent.value = R.string.fail_server_error
                    })
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, ProductType.RENT))
                    .subscribe({
                        isInterest.value = true
                        toastEvent.value = R.string.interest
                    }, {
                        toastEvent.value = R.string.fail_server_error
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
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun createRoom(postId: Int) {
        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, 0))
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .map { roomId ->
                    joinRoomUseCase.create(roomId)
                        .subscribe({ email ->
                            startChatActivityEvent.value = Bundle().apply {
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