package com.dsm.dsmmarketandroid.presentation.ui.main.comment

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.model.CommentModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
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
    val snackbarRetry = SingleLiveEvent<Unit>()

    val getCommentLogEvent = SingleLiveEvent<Bundle>()

    fun getCommentList(postId: Int, type: Int) {
        addDisposable(
            getCommentUseCase.create(GetCommentUseCase.Params(postId, type))
                .doOnNext { getCommentLogEvent.value = Bundle().apply { putInt(Analytics.POST_ID, postId) } }
                .subscribe({
                    if (it.isLocal) snackbarRetry.call()
                    commentModelMapper.mapFrom(it.data).let { model ->
                        listItems.value = model
                        commentCount.value = model.size
                        hideRefreshEvent.call()
                    }
                }, {
                    toastEvent.value = when (it) {
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }

    fun reportComment(nick: String) {
        dialogReportComment.value = nick
    }
}