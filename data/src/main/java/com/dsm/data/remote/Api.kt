package com.dsm.data.remote

import com.dsm.data.remote.entity.*
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("auth/login")
    fun login(@Body body: Any): Flowable<Response<Map<String, String>>>

    @GET("auth/login")
    fun autoLogin(): Flowable<Response<Unit>>

    @GET("auth/login")
    fun confirmPassword(@Query("password") password: String): Flowable<Response<Map<String, String>>>

    @GET("auth/mail")
    fun sendPasswordCode(@Query("email") email: String): Flowable<Response<Unit>>

    @POST("auth/mail")
    fun passwordCodeConfirm(@Body body: Any): Flowable<Response<Map<String, Int>>>

    @PATCH("account/password")
    fun changePassword(@Body params: Any): Flowable<Response<Unit>>

    @POST("account/join")
    fun signUp(@Body body: Any): Flowable<Response<Map<String, Int>>>

    @FormUrlEncoded
    @PATCH("account/nick")
    fun changeUserNick(@Field("nick") nick: String): Flowable<Response<Unit>>

    @GET("token")
    fun refreshToken(@Query("refresh_token") refreshToken: String): Flowable<Response<Map<String, Any>>>

    @GET("user")
    fun getUserNick(): Flowable<Response<Map<String, String>>>

    @GET("category")
    fun getPostCategory(): Flowable<PostCategoryListEntity>

    @Multipart
    @POST("post/rent")
    fun postRent(
        @Part img: MultipartBody.Part,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Flowable<Response<Unit>>

    @Multipart
    @POST("post/deal")
    fun postPurchase(
        @Part img: List<MultipartBody.Part>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Flowable<Response<Unit>>

    @GET("list/deal")
    fun getPurchaseList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Flowable<ProductListEntity>

    @GET("list/rent")
    fun getRentList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Flowable<ProductListEntity>

    @GET("post")
    fun getPurchaseDetail(
        @Query("postId") postId: Int,
        @Query("type") type: Int
    ): Flowable<Response<PurchaseDetailEntity>>

    @GET("post")
    fun getRentDetail(
        @Query("postId") postId: Int,
        @Query("type") type: Int
    ): Flowable<Response<RentDetailEntity>>

    @POST("post/comment")
    fun postComment(@Body params: Any): Flowable<Response<Unit>>

    @GET("comment")
    fun getComment(
        @Query("postId") postId: Int,
        @Query("type") type: Int
    ): Flowable<CommentListEntity>

    @FormUrlEncoded
    @PATCH("post/interest")
    fun interest(
        @Field("postId") postId: Int,
        @Field("type") type: Int
    ): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("post/uninterest")
    fun unInterest(
        @Field("postId") postId: Int,
        @Field("type") type: Int
    ): Flowable<Response<Unit>>
}