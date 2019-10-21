package com.dsm.dsmmarketandroid.presentation.ui.rentDetail

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.model.RecommendModel
import com.dsm.dsmmarketandroid.presentation.model.RentDetailModel
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
    private val recommendModelMapper: RecommendModelMapper,
    private val rentDetailModelMapper: RentDetailModelMapper
) : BaseViewModel() {

    val rentDetail = MutableLiveData<RentDetailModel>()
    val isInterest = MutableLiveData<Boolean>()

    val relatedList = MutableLiveData<List<RecommendModel>>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val toastInterestEvent = SingleLiveEvent<Any>()
    val toastUnInterestEvent = SingleLiveEvent<Any>()

    val startChatActivity = MutableLiveData<String>()

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
                    if (it is HttpException) {
                        if (it.code() == 410)
                            toastServerErrorEvent.call()
                    } else toastServerErrorEvent.call()
                })
        )
    }

    fun onClickInterest(postId: Int) {
        if (isInterest.value!!) {
            addDisposable(
                unInterestUseCase.create(UnInterestUseCase.Params(postId, 1))
                    .subscribe({
                        isInterest.value = false
                        toastUnInterestEvent.call()
                    }, {
                        toastServerErrorEvent.call()
                    })
            )
        } else {
            addDisposable(
                interestUseCase.create(InterestUseCase.Params(postId, 1))
                    .subscribe({
                        isInterest.value = true
                        toastInterestEvent.call()
                    }, {
                        toastServerErrorEvent.call()
                    })
            )
        }
    }

    fun getRelatedProduct(postId: Int) {
        addDisposable(
            getRelatedUseCase.create(GetRelatedUseCase.Params(postId, 1))
                .subscribe({
                    relatedList.value = recommendModelMapper.mapFrom(it)
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun createRoom(postId: Int) {
        addDisposable(
            createRoomUseCase.create(CreateRoomUseCase.Params(postId, 1))
                .subscribe({
                    startChatActivity.value = it
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}