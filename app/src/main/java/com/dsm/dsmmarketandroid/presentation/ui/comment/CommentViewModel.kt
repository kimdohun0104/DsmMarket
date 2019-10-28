package com.dsm.dsmmarketandroid.presentation.ui.comment

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.model.CommentModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class CommentViewModel(
    private val getCommentUseCase: GetCommentUseCase,
    private val commentModelMapper: CommentModelMapper
) : BaseViewModel() {

    val listItems = MutableLiveData<List<CommentModel>>()
    val commentCount = MutableLiveData<Int>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val dialogReportComment = MutableLiveData<String>()

    val hideRefreshEvent = SingleLiveEvent<Any>()

    fun getCommentList(postId: Int, type: Int) {
        addDisposable(
            getCommentUseCase.create(GetCommentUseCase.Params(postId, type))
                .map(commentModelMapper::mapFrom)
                .subscribe({
                    listItems.value = it
                    commentCount.value = it.size
                    hideRefreshEvent.call()
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }

    fun reportComment(nick: String) {
        dialogReportComment.value = nick
    }
}