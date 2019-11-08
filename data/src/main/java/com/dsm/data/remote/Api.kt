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
    fun confirmPassword(@Query("password") password: String): Flowable<Response<Unit>>

    @GET("auth/mail")
    fun sendTempPassword(@Query("email") email: String): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("account/password")
    fun changePassword(@Field("password") password: String): Flowable<Response<Unit>>

    @POST("account/join")
    fun signUp(@Body body: Any): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("account/nick")
    fun changeUserNick(@Field("nick") nick: String): Flowable<Response<Unit>>

    @GET("token")
    fun refreshToken(@Header("authorization") refreshToken: String): Flowable<Response<Map<String, Any>>>

    @GET("user/nick")
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
        @Query("pagesize") pageSize: Int,
        @Query("search") search: String,
        @Query("category") category: String
    ): Flowable<ProductListEntity>

    @GET("list/rent")
    fun getRentList(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("search") search: String,
        @Query("category") category: String
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

    @GET("list/interest")
    fun getInterest(@Query("type") type: Int): Flowable<ProductListEntity>

    @GET("user/list/deal")
    fun getMyPurchase(): Flowable<ProductListEntity>

    @GET("user/list/rent")
    fun getMyRent(): Flowable<ProductListEntity>

    @GET("list/related")
    fun getRelatedProduct(
        @Query("postId") postId: Int,
        @Query("type") type: Int
    ): Flowable<RecommendListEntity>

    @GET("list/recommend")
    fun getRecommendProduct(): Flowable<RecommendListEntity>

    @POST("report/post")
    fun reportPost(@Body params: Any): Flowable<Response<Unit>>

    @POST("report/comment")
    fun reportComment(@Body params: Any): Flowable<Response<Unit>>

    @DELETE("post/deal/{postId}")
    fun completePurchase(@Path("postId") postId: Int): Flowable<Response<Unit>>

    @DELETE("post/rent/{postId}")
    fun completeRent(@Path("postId") postId: Int): Flowable<Response<Unit>>

    @PATCH("post/deal")
    fun modifyPurchase(@Body params: Any): Flowable<Response<Unit>>

    @PATCH("post/rent")
    fun modifyRent(@Body params: Any): Flowable<Response<Unit>>

    @GET("deal/img")
    fun getPurchaseImage(@Query("postId") postId: Int): Flowable<PurchaseImageEntity>

    @GET("rent/img")
    fun getRentImage(@Query("postId") postId: Int): Flowable<RentImageEntity>

    @FormUrlEncoded
    @POST("room")
    fun createRoom(
        @Field("postId") postId: Int,
        @Field("type") type: Int
    ): Flowable<Map<String, Int>>

    @GET("room")
    fun getChatRoom(): Flowable<ChatRoomListEntity>

    @GET("room/join/{roomId}")
    fun joinRoom(@Path("roomId") roomId: Int) : Flowable<Response<HashMap<String, String>>>

    @GET("room/chatLog")
    fun getChatLog(
        @Query("roomId") roomId: Int,
        @Query("count") count: Int
    ) : Flowable<ChatLogListEntity>
}