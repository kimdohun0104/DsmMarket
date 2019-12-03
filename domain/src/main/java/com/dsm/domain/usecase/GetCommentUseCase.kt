package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Comment
import com.dsm.domain.error.Resource
import com.dsm.domain.service.CommentService
import io.reactivex.Flowable

class GetCommentUseCase(private val commentService: CommentService) : UseCase<GetCommentUseCase.Params, Resource<List<Comment>>>() {
    override fun create(data: Params): Flowable<Resource<List<Comment>>> =
        commentService.getCommentList(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}