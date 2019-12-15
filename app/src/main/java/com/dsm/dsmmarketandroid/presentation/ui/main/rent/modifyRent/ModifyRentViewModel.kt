package com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.ModifyRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
import kr.sdusb.libs.messagebus.MessageBus

class ModifyRentViewModel(
    private val getRentDetailUseCase: GetRentDetailUseCase,
    private val modifyRentUseCase: ModifyRentUseCase,
    private val rentDetailModelMapper: RentDetailModelMapper
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val photo = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val category = MutableLiveData<String>()

    val unit = MutableLiveData<String>().apply { value = "0" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val snackbarRetryEvent = SingleLiveEvent<Unit>()
    val toastEvent = SingleLiveEvent<Int>()

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
        || photo.value == null || content.isValueBlank() || category.isValueBlank()

    val isModifyEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(photo) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(category) { value = !isBlankExist() }
    }

    val startHour = MutableLiveData<String>()
    val startMinute = MutableLiveData<String>()
    val endHour = MutableLiveData<String>()
    val endMinute = MutableLiveData<String>()

    private fun getFormattedTime() = startHour.value + ":" + startMinute.value + "~" + endHour.value + ":" + endMinute.value

    val rentTime: MutableLiveData<String> = MediatorLiveData<String>().apply {
        addSource(startHour) { value = getFormattedTime() }
        addSource(startMinute) { value = getFormattedTime() }
        addSource(endHour) { value = getFormattedTime() }
        addSource(endMinute) { value = getFormattedTime() }
    }

    fun getRentDetail(postId: Int) {
        addDisposable(
            getRentDetailUseCase.create(postId)
                .subscribe({
                    if (it.isLocal) snackbarRetryEvent.call()

                    rentDetailModelMapper.mapFrom(it.data).let {detail ->
                        title.value = detail.title
                        price.value = detail.price.split(" ")[2].substring(0, detail.price.split(" ")[2].length - 1).replace(",", "")    // 1회당 100원
                        photo.value = detail.img
                        content.value = detail.content
                        category.value = detail.category
                        rentTime.value = detail.possibleTime
                    }
                }, {
                    toastEvent.value = when (it) {
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    fun modifyRent(postId: Int) {
        addDisposable(
            modifyRentUseCase.create(
                hashMapOf(
                    "postId" to postId,
                    "title" to title.value,
                    "content" to content.value,
                    "price" to unit.value + "/" + price.value,
                    "category" to category.value,
                    "possible_time" to rentTime.value
                )
            ).subscribe({
                MessageBus.getInstance().handle(MessageEvents.MODIFY_RENT_EVENT, null)
                finishActivityEvent.call()
            }, {
                toastEvent.value = R.string.fail_server_error
            })
        )
    }

    fun selectPriceUnit(unit: Int) {
        this.unit.value = unit.toString()
    }

    fun setCategory(category: String) {
        this.category.value = category
    }
}