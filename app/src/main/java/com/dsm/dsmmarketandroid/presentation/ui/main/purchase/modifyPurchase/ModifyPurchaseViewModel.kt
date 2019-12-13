package com.dsm.dsmmarketandroid.presentation.ui.main.purchase.modifyPurchase

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.ModifyPurchaseUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.util.ListLiveData
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
import kr.sdusb.libs.messagebus.MessageBus

class ModifyPurchaseViewModel(
    private val getPurchaseDetailUseCase: GetPurchaseDetailUseCase,
    private val modifyPurchaseUseCase: ModifyPurchaseUseCase,
    private val purchaseDetailModelMapper: PurchaseDetailModelMapper
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val imageList = ListLiveData<String>()

    val finishActivityEvent = SingleLiveEvent<Any>()
    val snackbarRetryEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
            || content.isValueBlank() || category.isValueBlank()

    val isModifyEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(category) { value = !isBlankExist() }
    }

    fun getPurchaseDetail(postId: Int) {
        addDisposable(
            getPurchaseDetailUseCase.create(postId)
                .subscribe({
                    if (it.isLocal) snackbarRetryEvent.call()

                    purchaseDetailModelMapper.mapFrom(it.data).let { detail ->
                        title.value = detail.title
                        price.value = detail.price.substring(0, detail.price.length - 1).replace(",", "")    // 100원
                        category.value = detail.category
                        content.value = detail.content
                        imageList.value = detail.img as ArrayList<String>
                    }
                }, {
                    toastEvent.value = when (it) {
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    fun modifyPurchase(postId: Int) {
        addDisposable(
            modifyPurchaseUseCase.create(
                hashMapOf(
                    "postId" to postId,
                    "title" to title.value,
                    "content" to content.value,
                    "price" to price.value,
                    "category" to category.value
                )
            ).subscribe({
                MessageBus.getInstance().handle(MessageEvents.MODIFY_PURCHASE_EVENT, null)
                finishActivityEvent.call()
            }, {
                toastEvent.value = when (it) {
                    is UnauthorizedException -> R.string.fail_unauthorized
                    else -> R.string.fail_server_error
                }
            })
        )
    }

    fun setCategory(category: String) {
        this.category.value = category
    }
}
