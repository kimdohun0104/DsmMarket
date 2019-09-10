package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Comment
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable

class GetPurchaseCommentUseCase(private val commentRepository: CommentRepository) : UseCase<Int, Comment>() {
    override fun create(data: Int): Flowable<Comment> =
        commentRepository.getPurchaseComment(data)
}