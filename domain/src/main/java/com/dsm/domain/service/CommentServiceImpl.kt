package com.dsm.domain.service

import com.dsm.domain.entity.Comment
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.CommentRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class CommentServiceImpl(
    private val repository: CommentRepository,
    private val errorHandler: ErrorHandler
): CommentService {

    override fun getCommentList(postId: Int, type: Int): Flowable<List<Comment>> =
        repository.getRemoteCommentList(postId, type)
            .doOnNext { repository.addLocalComment(it, postId, type).subscribe() }
            .onErrorReturn { repository.getLocalCommentList(postId, type) }

    override fun postComment(param: Any): Flowable<Resource<Unit>> =
        repository.postComment(param).toResource(errorHandler)
}