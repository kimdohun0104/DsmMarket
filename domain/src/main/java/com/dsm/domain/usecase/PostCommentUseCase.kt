package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable

class PostCommentUseCase(private val commentRepository: CommentRepository) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        commentRepository.postComment(data)
}