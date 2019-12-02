package com.dsm.data.dataSource.comment

import com.dsm.data.local.db.entity.CommentRoomEntity
import com.dsm.data.remote.entity.CommentListEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface CommentDataSource {
    fun postComment(params: Any): Flowable<Unit>

    fun getRemoteCommentList(postId: Int, type: Int): Flowable<CommentListEntity>

    fun getLocalCommentList(postId: Int, type: Int): List<CommentRoomEntity>

    fun addLocalComment(comment: List<CommentRoomEntity>): Completable
}