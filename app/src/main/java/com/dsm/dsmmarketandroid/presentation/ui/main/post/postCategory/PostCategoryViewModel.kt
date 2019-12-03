package com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetPostCategoryUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.model.PostCategoryModel
import com.dsm.dsmmarketandroid.presentation.util.ListLiveData
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class PostCategoryViewModel(
    private val getPostCategoryUseCase: GetPostCategoryUseCase,
    private val postCategoryModelMapper: PostCategoryModelMapper
) : BaseViewModel() {

    val categoryList = ListLiveData<PostCategoryModel>()
    val selectedCategory = MutableLiveData<String>()

    val toastEvent = SingleLiveEvent<Int>()

    val isProgressVisible = MutableLiveData<Boolean>().apply { value = true }

    fun getPostCategory() {
        addDisposable(
            getPostCategoryUseCase.create(Unit)
                .map(postCategoryModelMapper::mapFrom)
                .subscribe({
                    categoryList.addAll(it)
                    isProgressVisible.value = false
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun selectCategory(category: String) {
        selectedCategory.value = category
    }
}