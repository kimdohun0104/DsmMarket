package com.dsm.data.dataSource.post

import com.dsm.data.remote.entity.PostCategoryListEntity
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface PostDataSource {
    fun getPostCategory(): Flowable<PostCategoryListEntity>

    fun postRent(img: MultipartBody.Part, params: Map<String, RequestBody>): Flowable<Response<Unit>>
}