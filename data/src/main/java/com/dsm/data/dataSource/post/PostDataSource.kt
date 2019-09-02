package com.dsm.data.dataSource.post

import com.dsm.data.remote.entity.PostCategoryEntity
import com.dsm.data.remote.entity.PostCategoryListEntity
import io.reactivex.Flowable

interface PostDataSource {
    fun getPostCategory(): Flowable<PostCategoryListEntity>
}