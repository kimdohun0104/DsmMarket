package com.dsm.data.repository

import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.mapper.CommentMapper
import com.dsm.domain.entity.Comment
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class CommentRepositoryImpl(
    private val commentDataSource: CommentDataSource,
    private val commentMapper: CommentMapper
) : CommentRepository {
    
    override fun postComment(params: Any): Flowable<Unit> =
        commentDataSource.postComment(params)

    override fun getRemoteCommentList(postId: Int, type: Int): Flowable<List<Comment>> =
        commentDataSource.getRemoteCommentList(postId, type).map(commentMapper::mapFrom)

    override fun getLocalCommentList(postId: Int, type: Int): List<Comment> =
        commentDataSource.getLocalCommentList(postId, type).map(commentMapper::mapFrom)

    override fun addLocalComment(comment: List<Comment>, postId: Int, type: Int): Completable =
        commentDataSource.addLocalComment(commentMapper.mapFrom(comment, postId, type))
}