package com.dsm.data.dataSource.post

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.PostCategoryEntity
import com.dsm.data.remote.entity.PostCategoryListEntity
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class PostDataSourceImpl(private val api: Api) : PostDataSource {
    override fun getPostCategory(): Flowable<PostCategoryListEntity> =
        api.getPostCategory().addSchedulers()
}