package com.dsm.data.repository

import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.mapper.CommentMapper
import com.dsm.domain.entity.Comment
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource) : CommentRepository {

    private val commentMapper = CommentMapper()

    override fun postComment(params: Any): Flowable<Unit> =
        commentDataSource.postComment(params).map {  }

    override fun getPurchaseComment(postId: Int): Flowable<Comment> =
        commentDataSource.getPurchaseComment(postId).map(commentMapper::mapFrom)

    override fun getRentComment(postId: Int): Flowable<Comment> =
        commentDataSource.getRentComment(postId).map(commentMapper::mapFrom)
}