package com.dsm.dsmmarketandroid.presentation.ui.postCategory

import android.util.Log
import com.dsm.domain.usecase.GetPostCategoryUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper

class PostCategoryViewModel(
    private val getPostCategoryUseCase: GetPostCategoryUseCase,
    private val postCategoryModelMapper: PostCategoryModelMapper
) : BaseViewModel() {

    fun getPostCategory() {
        addDisposable(
            getPostCategoryUseCase.create(Unit).subscribe({
                val categoryList = postCategoryModelMapper.mapFrom(it)
            }, {
            })
        )
    }
}