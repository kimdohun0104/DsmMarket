package com.dsm.dsmmarketandroid.presentation.ui.modify.purchase

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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
                .map(purchaseDetailModelMapper::mapFrom)
                .subscribe({
                    title.value = it.title
                    price.value = it.price.substring(0, it.price.length - 1)    // 100원
                    category.value = it.category
                    content.value = it.content
                    imageList.value = it.img as ArrayList<String>
                }, {
                    toastEvent.value = R.string.fail_server_error
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
                toastEvent.value = R.string.fail_server_error
            })
        )
    }

    fun setCategory(category: String) {
        this.category.value = category
    }
}
