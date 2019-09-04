package com.dsm.dsmmarketandroid.presentation.ui.post.postRent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class PostRentViewModel : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val photo = MutableLiveData<String>()
    val possibleTime = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val category = MutableLiveData<String>()

    val isCategorySelected: LiveData<Boolean> = Transformations.map(category) { it != "" }

    val startHour = MutableLiveData<String>()
    val startMinute = MutableLiveData<String>()
    val endHour = MutableLiveData<String>()
    val endMinute = MutableLiveData<String>()

    private fun getFormattedTime() = startHour.value + " : " + startMinute.value + " ~ " + endHour.value + " : " + endMinute.value

    val rentTime: MutableLiveData<String> = MediatorLiveData<String>().apply {
        addSource(startHour) { value = getFormattedTime() }
        addSource(startMinute) { value = getFormattedTime() }
        addSource(endHour) { value = getFormattedTime() }
        addSource(endMinute) { value = getFormattedTime() }
    }

    private fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
            || photo.value == null || possibleTime.isValueBlank()
            || content.isValueBlank() || tag.isValueBlank() || category.isValueBlank()

    val isPostEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(photo) { value = !isBlankExist() }
        addSource(possibleTime) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(tag) { value = !isBlankExist() }
        addSource(category) { value = !isBlankExist() }
    }

    fun post() {
    }
}