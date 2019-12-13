package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.CommentService
import io.reactivex.Flowable

class PostCommentUseCase(private val commentService: CommentService) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        commentService.postComment(data)
}