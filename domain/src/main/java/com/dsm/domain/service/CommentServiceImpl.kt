package com.dsm.domain.service

import com.dsm.domain.entity.Comment
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable

class CommentServiceImpl(
    private val repository: CommentRepository,
    private val errorHandler: ErrorHandler
) : CommentService {

    override fun getCommentList(postId: Int, type: Int): Flowable<Success<List<Comment>>> =
        repository.getRemoteCommentList(postId, type)
            .doOnNext { repository.addLocalComment(it, postId, type).subscribe() }
            .toSuccess(errorHandler)
            .onErrorReturn {
                repository.getLocalCommentList(postId, type)?.let {
                    Success(it, true)
                }
            }

    override fun postComment(param: Any): Flowable<Unit> =
        repository.postComment(param).handleError(errorHandler)
}