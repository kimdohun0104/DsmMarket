package com.dsm.data.dataSource.comment

import com.dsm.data.remote.entity.CommentListEntity
import io.reactivex.Flowable
import retrofit2.Response

interface CommentDataSource {
    fun postComment(params: Any): Flowable<Response<Unit>>

    fun getCommentList(postId: Int, type: Int): Flowable<CommentListEntity>
}