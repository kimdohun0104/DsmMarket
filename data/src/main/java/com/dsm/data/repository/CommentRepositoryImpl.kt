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

    override fun getCommentList(postId: Int, type: Int): Flowable<List<Comment>> =
        commentDataSource.getCommentList(postId, type).map(commentMapper::mapFrom)
}