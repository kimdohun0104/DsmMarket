package com.dsm.data.dataSource.comment

import io.reactivex.Flowable
import retrofit2.Response

interface CommentDataSource {
    fun postComment(params: Any): Flowable<Response<Unit>>
}