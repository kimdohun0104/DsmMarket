package com.dsm.dsmmarketandroid.presentation.ui.comment

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.R
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

    val toastEvent = SingleLiveEvent<Int>()
    val dialogReportComment = SingleLiveEvent<String>()
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
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }

    fun reportComment(nick: String) {
        dialogReportComment.value = nick
    }
}