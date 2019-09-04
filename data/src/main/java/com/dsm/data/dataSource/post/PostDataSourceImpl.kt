package com.dsm.data.dataSource.post

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PostCategoryListEntity
import io.reactivex.Flowable

class PostDataSourceImpl(private val api: Api) : PostDataSource {
    override fun getPostCategory(): Flowable<PostCategoryListEntity> =
        api.getPostCategory().addSchedulers()
}