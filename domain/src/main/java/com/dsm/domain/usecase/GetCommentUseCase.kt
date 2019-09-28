package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Comment
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable

class GetCommentUseCase(private val commentRepository: CommentRepository) : UseCase<GetCommentUseCase.Params, List<Comment>>() {
    override fun create(data: Params): Flowable<List<Comment>> =
        commentRepository.getCommentList(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}