package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.PostPurchaseUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.ListLiveData
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PostPurchaseViewModel(private val postPurchaseUseCase: PostPurchaseUseCase) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val imageList = ListLiveData<String>()

    val isCategorySelected: LiveData<Boolean> = Transformations.map(category) { it != "" }

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
        || content.isValueBlank() || imageList.value?.size == 0 || category.isValueBlank()

    val isPostEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(imageList) { value = !isBlankExist() }
        addSource(category) { value = isBlankExist() }
    }

    val finishActivityEvent = SingleLiveEvent<Any>()

    val toastEvent = SingleLiveEvent<Int>()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    fun post() {
        val multipartImageList = arrayListOf<MultipartBody.Part>()
        imageList.value!!.forEach {
            val imageFile = File(it)
            multipartImageList.add(MultipartBody.Part.createFormData("img", imageFile.name, RequestBody.create(MediaType.parse("image/*"), imageFile)))
        }

        showLoadingDialogEvent.call()

        addDisposable(
            postPurchaseUseCase.create(
                PostPurchaseUseCase.Params(
                    multipartImageList,
                    mapOf(
                        "title" to createTextPlain(title.value),
                        "content" to createTextPlain(content.value),
                        "price" to createTextPlain(price.value),
                        "category" to createTextPlain(category.value)
                    )
                )
            )
                .doFinally { hideLoadingDialogEvent.call() }
                .subscribe({
                    finishActivityEvent.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    private fun createTextPlain(value: String?): RequestBody =
        RequestBody.create(MediaType.parse("text/plain"), value ?: "")

    fun imageRemovedAt(index: Int) {
        imageList.removeAt(index)
    }

    fun setImageList(imageList: ArrayList<String>) {
        this.imageList.value = imageList
    }

    fun setCategory(category: String) {
        this.category.value = category
    }
}
