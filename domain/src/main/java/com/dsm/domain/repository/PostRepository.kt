package com.dsm.domain.repository

import com.dsm.domain.entity.PostCategory
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface PostRepository {
    fun getPostCategory(): Flowable<List<PostCategory>>

    fun postRent(img: MultipartBody.Part, params: Map<String, RequestBody>): Flowable<Int>
}