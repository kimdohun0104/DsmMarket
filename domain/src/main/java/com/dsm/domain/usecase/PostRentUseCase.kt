package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PostRepository
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostRentUseCase(private val postRepository: PostRepository) : UseCase<PostRentUseCase.Params, Int>() {
    override fun create(data: Params): Flowable<Int> =
        postRepository.postRent(data.img, data.params)

    data class Params(val img: MultipartBody.Part, val params: Map<String, RequestBody>)
}