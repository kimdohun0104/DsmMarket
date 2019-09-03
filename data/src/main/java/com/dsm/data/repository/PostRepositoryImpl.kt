package com.dsm.data.repository

import com.dsm.data.dataSource.post.PostDataSource
import com.dsm.data.mapper.PostCategoryMapper
import com.dsm.domain.entity.PostCategory
import com.dsm.domain.repository.PostRepository
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostRepositoryImpl(private val postDataSource: PostDataSource) : PostRepository {

    private val postCategoryMapper = PostCategoryMapper()

    override fun getPostCategory(): Flowable<List<PostCategory>> =
        postDataSource.getPostCategory().map { postCategoryMapper.mapFrom(it.category) }
}