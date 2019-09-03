package com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.ListLiveData

class PostPurchaseViewModel : BaseViewModel() {

    val title = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    //    val category = MutableLiveData<String>()
    val tag = MutableLiveData<String>()
    val imageList = ListLiveData<Uri>()

    private fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

    private fun isBlankExist() = title.isValueBlank() || price.isValueBlank()
            || content.isValueBlank() || tag.isValueBlank()
            || imageList.value?.size == 0

    val isPostEnable = MediatorLiveData<Boolean>().apply {
        addSource(title) { value = !isBlankExist() }
        addSource(price) { value = !isBlankExist() }
        addSource(content) { value = !isBlankExist() }
        addSource(tag) { value = !isBlankExist() }
        addSource(imageList) { value = !isBlankExist() }
        // addSource(category) { value = isBlankExist() }
    }

    fun post() {
//        val multipartImageList = arrayListOf<MultipartBody.Part>()
//
//        imageList.value?.forEach {
//            val imageFile = File(it.path!!)
//            val requestImageFile = RequestBody.create(MediaType.parse("image/*"), imageFile)
//            multipartImageList.add(MultipartBody.Part.createFormData("file", imageFile.name, requestImageFile))
//        }
    }

    fun imageRemovedAt(index: Int) {
        imageList.removeAt(index)
    }
}