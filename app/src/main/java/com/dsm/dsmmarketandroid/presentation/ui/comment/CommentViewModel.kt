package com.dsm.dsmmarketandroid.presentation.ui.comment

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.model.CommentModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class CommentViewModel(
    private val getPurchaseCommentUseCase: GetCommentUseCase,
    private val commentModelMapper: CommentModelMapper
) : BaseViewModel() {

    val listItems = MutableLiveData<List<CommentModel>>()
    val commentCount = MutableLiveData<Int>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun getCommentList(postId: Int, type: Int) {
        addDisposable(
            getPurchaseCommentUseCase.create(GetCommentUseCase.Params(postId, type))
                .subscribe({
                    val commentList = commentModelMapper.mapFrom(it)
                    listItems.value = commentList
                    commentCount.value = commentList.size
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}