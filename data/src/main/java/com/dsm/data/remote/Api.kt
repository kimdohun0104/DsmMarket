package com.dsm.data.remote

import com.dsm.data.remote.entity.PostCategoryListEntity
import com.dsm.data.remote.entity.ProductListEntity
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Multipart
    @POST("post/add/rent")
    fun postRent(
        @Part img: MultipartBody.Part,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Flowable<Response<Unit>>

    @Multipart
    @POST("post/add/deal")
    fun postPurchase(
        @Part img: List<MultipartBody.Part>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Flowable<Response<Unit>>

    @GET("get/list/deal")
    fun getPurchaseList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Flowable<ProductListEntity>

    @GET("get/list/rent")
    fun getRentList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Flowable<ProductListEntity>
}