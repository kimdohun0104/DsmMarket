package com.dsm.dsmmarketandroid.presentation.ui.main.post.postRent

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PostRentViewModel(private val postRentUseCase: PostRentUseCase) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val photo = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val category = MutableLiveData<String>()

    val unit = MutableLiveData<String>().apply { value = "0" }

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
        || photo.value == null || content.isValueBlank() || category.isValueBlank()

    val isPostEnable = MediatorLiveData<Boolean>().apply {
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

    val isCategorySelected: LiveData<Boolean> = Transformations.map(category) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()

    val toastEvent = SingleLiveEvent<Int>()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    val postRentLogEvent = SingleLiveEvent<Bundle>()

    fun post() {
        val imageFile = File(photo.value!!)

        addDisposable(
            postRentUseCase.create(
                PostRentUseCase.Params(
                    MultipartBody.Part.createFormData("img", imageFile.name, imageFile.asRequestBody("image/*".toMediaTypeOrNull())),
                    mapOf(
                        "title" to createTextPlain(title.value),
                        "content" to createTextPlain(content.value),
                        "price" to createTextPlain(unit.value + "/" + price.value),
                        "category" to createTextPlain(category.value),
                        "possible_time" to createTextPlain(rentTime.value ?: "")
                    )
                )
            )
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doFinally { hideLoadingDialogEvent.call() }
                .doOnNext {
                    postRentLogEvent.value = Bundle().apply {
                        putString(Analytics.TITLE, title.value)
                        putInt(Analytics.PRICE, price.value?.toInt() ?: -1)
                        putString(Analytics.CATEGORY, category.value)
                    }
                }
                .subscribe({
                    finishActivityEvent.call()
                }, {
                    toastEvent.value = when (it) {
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    private fun createTextPlain(value: String?): RequestBody = value!!.toRequestBody("text/plain".toMediaTypeOrNull())

    fun selectPriceUnit(unit: Int) {
        this.unit.value = unit.toString()
    }

    fun setPhoto(photo: String) {
        this.photo.value = photo
    }

    fun setCategory(category: String) {
        this.category.value = category
    }
}