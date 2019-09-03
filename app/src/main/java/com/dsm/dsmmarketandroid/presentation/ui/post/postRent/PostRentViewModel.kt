package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class PostRentViewModel : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val photo = MutableLiveData<String>()
    val possibleTime = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val tag = MutableLiveData<String>()

    val startHour = MutableLiveData<String>().apply { value = "1" }
    val startMinute = MutableLiveData<String>().apply { value = "00" }
    val endHour = MutableLiveData<String>().apply { value = "1" }
    val endMinute = MutableLiveData<String>().apply { value = "00" }

    private fun getFormatedTime() = startHour.value + " : " + startMinute.value + " ~ " + endHour.value + " : " + endMinute.value

    val rentTime: MutableLiveData<String> = MediatorLiveData<String>().apply {
        addSource(startHour) { value = getFormatedTime() }
        addSource(startMinute) { value = getFormatedTime() }
        addSource(endHour) { value = getFormatedTime() }
        addSource(endMinute) { value = getFormatedTime() }
    }

    private fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
            || photo.value == null || possibleTime.isValueBlank()
            || content.isValueBlank() || tag.isValueBlank()

    val isPostEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(photo) { value = !isBlankExist() }
        addSource(possibleTime) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(tag) { value = !isBlankExist() }
    }

    fun post() {
    }
}