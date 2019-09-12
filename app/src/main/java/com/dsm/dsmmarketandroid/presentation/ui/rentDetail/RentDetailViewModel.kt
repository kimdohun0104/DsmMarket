package com.dsm.dsmmarketandroid.presentation.ui.rentDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.model.RentDetailModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class RentDetailViewModel(
    private val getRentDetailUseCase: GetRentDetailUseCase,
    private val rentDetailModelMapper: RentDetailModelMapper
) : BaseViewModel() {

    val rentDetail = MutableLiveData<RentDetailModel>()
    val isInterest = Transformations.map(rentDetail) { it.isInterest }

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun getRentDetail(postId: Int) {
        addDisposable(
            getRentDetailUseCase.create(postId)
                .subscribe({
                    val result = rentDetailModelMapper.mapFrom(it)
                    rentDetail.value = result
                }, {
                    if (it is HttpException) {
                        if (it.code() == 410)
                            toastServerErrorEvent.call()
                    } else toastServerErrorEvent.call()
                })
        )
    }
}