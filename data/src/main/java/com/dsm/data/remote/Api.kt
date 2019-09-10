package com.dsm.data.remote

import com.dsm.data.remote.entity.PostCategoryListEntity
import com.dsm.data.remote.entity.ProductListEntity
import com.dsm.data.remote.entity.PurchaseDetailEntity
import com.dsm.data.remote.entity.RentDetailEntity
import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query

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

    @POST("comment")
    fun postComment(@Body params: Any): Flowable<Response<Unit>>
}