package com.dsm.data.dataSource.comment

import com.dsm.data.remote.entity.CommentEntity
import io.reactivex.Flowable
import retrofit2.Response

interface CommentDataSource {
    fun postComment(params: Any): Flowable<Response<Unit>>

    fun getPurchaseComment(postId: Int): Flowable<CommentEntity>

    fun getRentComment(postId: Int): Flowable<CommentEntity>
}