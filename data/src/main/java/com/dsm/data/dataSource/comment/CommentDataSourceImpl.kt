package com.dsm.data.dataSource.comment

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.CommentEntity
import io.reactivex.Flowable
import retrofit2.Response

class CommentDataSourceImpl(private val api: Api) : CommentDataSource {
    override fun postComment(params: Any): Flowable<Response<Unit>> =
        api.postComment(params).addSchedulers()

    override fun getPurchaseComment(postId: Int): Flowable<CommentEntity> =
        api.getComment(postId, 0).addSchedulers()

    override fun getRentComment(postId: Int): Flowable<CommentEntity> =
        api.getComment(postId, 1).addSchedulers()
}