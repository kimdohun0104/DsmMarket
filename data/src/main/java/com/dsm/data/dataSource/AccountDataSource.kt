package com.dsm.data.dataSource

import io.reactivex.Flowable
import retrofit2.Response

interface AccountDataSource {

    fun login(body: Any): Flowable<Response<Map<String, Any>>>

    fun signUp(body: Any): Flowable<Response<Map<String, Int>>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun sendEmail(email: String): Flowable<Response<Unit>>

    fun mailConfirm(body: Any): Flowable<Response<Unit>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)
}