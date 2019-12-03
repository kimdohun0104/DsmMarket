package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.PostService
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostPurchaseUseCase(private val postService: PostService) : UseCase<PostPurchaseUseCase.Params, Resource<Unit>>() {
    override fun create(data: Params): Flowable<Resource<Unit>> =
        postService.postPurchase(data.img, data.params)

    data class Params(val img: List<MultipartBody.Part>, val params: Map<String, RequestBody>)
}