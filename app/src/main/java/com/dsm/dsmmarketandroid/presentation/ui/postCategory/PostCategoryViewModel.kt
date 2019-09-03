package com.dsm.dsmmarketandroid.presentation.ui.postCategory

import com.dsm.domain.usecase.GetPostCategoryUseCase
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

    val serverErrorEvent = SingleLiveEvent<Any>()

    fun getPostCategory() {
        addDisposable(
            getPostCategoryUseCase.create(Unit).subscribe({
                categoryList.addAll(postCategoryModelMapper.mapFrom(it))
            }, {
                serverErrorEvent.call()
            })
        )
    }
}