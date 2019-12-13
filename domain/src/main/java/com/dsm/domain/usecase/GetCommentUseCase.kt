package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Comment
import com.dsm.domain.error.Success
import com.dsm.domain.service.CommentService
import io.reactivex.Flowable

class GetCommentUseCase(private val commentService: CommentService) : UseCase<GetCommentUseCase.Params, Success<List<Comment>>>() {
    override fun create(data: Params): Flowable<Success<List<Comment>>> =
        commentService.getCommentList(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}