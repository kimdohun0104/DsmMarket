package com.dsm.data.dataSource.comment

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.CommentDao
import com.dsm.data.local.db.entity.CommentRoomEntity
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.CommentListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

class CommentDataSourceImpl(
    private val api: Api,
    private val commentDao: CommentDao
) : CommentDataSource {

    override fun postComment(params: Any): Flowable<Unit> =
        api.postComment(params).addSchedulers()

    override fun getRemoteCommentList(postId: Int, type: Int): Flowable<CommentListEntity> =
        api.getComment(postId, type).addSchedulers()

    override fun getLocalCommentList(postId: Int, type: Int): List<CommentRoomEntity> =
        commentDao.getCommentList(postId, type)

    override fun addLocalComment(comment: List<CommentRoomEntity>): Completable =
        commentDao.addComment(comment)

}