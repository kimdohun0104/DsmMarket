package com.dsm.data.remote

import com.dsm.data.remote.entity.PostCategoryEntity
import com.dsm.data.remote.entity.PostCategoryListEntity
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("account/auth/login")
    fun login(@Body body: Any): Flowable<Response<Map<String, Any>>>

    @GET("account/auth/login")
    fun login(): Flowable<Response<Unit>>

    @POST("account/auth/join")
    fun signUp(@Body body: Any): Flowable<Response<Map<String, Int>>>

    @FormUrlEncoded
    @POST("mail/send")
    fun sendPasswordCode(@Field("email") email: String): Flowable<Response<Unit>>

    @POST("mail/confirm")
    fun passwordCodeConfirm(@Body body: Any): Flowable<Response<Unit>>

    @FormUrlEncoded
    @POST("account/edit/password")
    fun changePassword(@Field("password") newPassword: String): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("mail/password")
    fun changePassword(
        @Field("email") email: String,
        @Field("password") newPassword: String
    ): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("account/edit/nick")
    fun changeUserNick(@Field("nick") newNick: String): Flowable<Response<Unit>>

    @FormUrlEncoded
    @POST("account/auth/token")
    fun refreshToken(@Field("refresh_token") refreshToken: String): Flowable<Response<Map<String, Any>>>

    @GET("account/get/nick")
    fun getUserNick(): Flowable<Response<Map<String, String>>>

    @GET("post/category")
    fun getPostCategory(): Flowable<PostCategoryListEntity>
}