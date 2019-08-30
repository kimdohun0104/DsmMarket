package com.dsm.data.remote

import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("account/auth/login")
    fun login(@Body body: Any): Flowable<Response<Map<String, Any>>>

    @POST("account/auth/join")
    fun join(@Body body: Any): Flowable<Response<Map<String, Int>>>

    @PATCH("account/edit/password")
    fun editPassword(@Body body: Any): Flowable<Response<Unit>>

    @FormUrlEncoded
    @PATCH("account/edit/name")
    fun editName(@Field("new_nick") newName: String): Flowable<Response<Map<String, Any>>>

    @FormUrlEncoded
    @POST("mail/send")
    fun sendMail(@Field("email") email: String): Flowable<Response<Unit>>

    @POST("mail/confirm")
    fun mailConfirm(@Body body: Any): Flowable<Response<Unit>>

    @FormUrlEncoded
    @POST("account/auth/token")
    fun refreshToken(@Field("refresh_token") refreshToken: String): Flowable<Response<Map<String, Any>>>
}