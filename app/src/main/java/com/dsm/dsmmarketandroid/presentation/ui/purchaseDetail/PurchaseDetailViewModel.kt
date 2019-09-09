package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class PurchaseDetailViewModel(
    private val getPurchaseDetailUseCase: GetPurchaseDetailUseCase,
    private val purchaseDetailModelMapper: PurchaseDetailModelMapper
) : BaseViewModel() {

    val imageList = MutableLiveData<List<String>>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val createdAt = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val commentCount = MutableLiveData<Int>()
    val author = MutableLiveData<String>()

    val toastNonExistEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun getPurchaseDetail(postId: Int) {
        addDisposable(
            getPurchaseDetailUseCase.create(postId)
                .subscribe({
                    val result = purchaseDetailModelMapper.mapFrom(it)
                    imageList.value = result.img
                    title.value = result.title
                    content.value = result.content
                    createdAt.value = result.createdAt
                    price.value = result.price
                    commentCount.value = result.commentCount
                    author.value = result.author
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
}