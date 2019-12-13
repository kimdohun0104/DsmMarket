package com.dsm.domain.service

import com.dsm.domain.entity.PostCategory
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface PostService {

    fun getPostCategory(): Flowable<List<PostCategory>>

    fun postRent(img: MultipartBody.Part, params: Map<String, RequestBody>): Flowable<Unit>

    fun postPurchase(img: List<MultipartBody.Part>, params: Map<String, RequestBody>): Flowable<Unit>
}