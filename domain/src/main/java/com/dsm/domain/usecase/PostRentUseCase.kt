package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.PostService
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostRentUseCase(private val postService: PostService) : UseCase<PostRentUseCase.Params, Unit>() {
    override fun create(data: Params): Flowable<Unit> =
        postService.postRent(data.img, data.params)

    data class Params(val img: MultipartBody.Part, val params: Map<String, RequestBody>)
}