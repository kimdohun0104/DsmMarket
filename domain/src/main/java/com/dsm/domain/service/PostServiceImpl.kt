package com.dsm.domain.service

import com.dsm.domain.entity.PostCategory
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.repository.PostRepository
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostServiceImpl(
    private val repository: PostRepository,
    private val errorHandler: ErrorHandler
) : PostService {

    override fun getPostCategory(): Flowable<List<PostCategory>> =
        repository.getPostCategory()

    override fun postRent(img: MultipartBody.Part, params: Map<String, RequestBody>): Flowable<Unit> =
        repository.postRent(img, params).handleError(errorHandler)

    override fun postPurchase(img: List<MultipartBody.Part>, params: Map<String, RequestBody>): Flowable<Unit> =
        repository.postPurchase(img, params).handleError(errorHandler)
}