package com.dsm.domain.repository

import com.dsm.domain.entity.PostCategory
import io.reactivex.Flowable

interface PostRepository {
    fun getPostCategory(): Flowable<List<PostCategory>>
}