package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.PostCategory
import com.dsm.domain.service.PostService
import io.reactivex.Flowable

class GetPostCategoryUseCase(private val postService: PostService) : UseCase<Unit, List<PostCategory>>() {
    override fun create(data: Unit): Flowable<List<PostCategory>> =
        postService.getPostCategory()

}