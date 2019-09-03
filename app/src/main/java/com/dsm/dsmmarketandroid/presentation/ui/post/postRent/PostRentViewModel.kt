package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import android.util.Log
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

    val startHour = MutableLiveData<String>()
    val startMinute = MutableLiveData<String>()
    val endHour = MutableLiveData<String>()
    val endMinute = MutableLiveData<String>()

    private fun getRentTime() = startHour.value + " : " + startMinute.value + " ~ " + endHour.value + " : " + endMinute.value

    val rentTime = MediatorLiveData<String>().apply {
        addSource(startHour) { value = getRentTime() }
        addSource(startMinute) { value = getRentTime() }
        addSource(endHour) { value = getRentTime() }
        addSource(endMinute) { value = getRentTime() }
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