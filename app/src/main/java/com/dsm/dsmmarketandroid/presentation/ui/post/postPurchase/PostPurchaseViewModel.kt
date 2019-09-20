package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.PostPurchaseUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.ListLiveData
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
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

    private fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

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
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun post() {
        val multipartImageList = arrayListOf<MultipartBody.Part>()
        imageList.value!!.forEach {
            val imageFile = File(it)
            multipartImageList.add(MultipartBody.Part.createFormData("file", imageFile.name, RequestBody.create(MediaType.parse("image/*"), imageFile)))
        }

        addDisposable(
            postPurchaseUseCase.create(
                PostPurchaseUseCase.Params(
                    multipartImageList,
                    mapOf(
                        "title" to RequestBody.create(MediaType.parse("text/plain"), title.value!!),
                        "content" to RequestBody.create(MediaType.parse("text/plain"), content.value!!),
                        "price" to RequestBody.create(MediaType.parse("text/plain"), price.value!!),
                        "category" to RequestBody.create(MediaType.parse("text/plain"), category.value!!)
                    )
                )
            ).subscribe({
                finishActivityEvent.call()
            }, {
                toastServerErrorEvent.call()
            })
        )
    }

    fun imageRemovedAt(index: Int) {
        imageList.removeAt(index)
    }
}