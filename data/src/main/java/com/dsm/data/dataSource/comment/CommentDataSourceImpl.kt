package com.dsm.data.dataSource.comment

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.CommentListEntity
import io.reactivex.Flowable
import retrofit2.Response

class CommentDataSourceImpl(private val api: Api) : CommentDataSource {
    override fun postComment(params: Any): Flowable<Response<Unit>> =
        api.postComment(params).addSchedulers()

    override fun getCommentList(postId: Int, type: Int): Flowable<CommentListEntity> =
        api.getComment(postId, type).addSchedulers()

}