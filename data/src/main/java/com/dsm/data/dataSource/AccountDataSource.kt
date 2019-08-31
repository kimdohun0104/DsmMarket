package com.dsm.data.dataSource

import io.reactivex.Flowable
import retrofit2.Response

interface AccountDataSource {

    fun login(body: Any): Flowable<Response<Map<String, Any>>>

    fun signUp(body: Any): Flowable<Response<Map<String, Int>>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun sendPasswordCode(email: String): Flowable<Response<Unit>>

    fun passwordCodeConfirm(body: Any): Flowable<Response<Unit>>

    fun changePassword(newPassword: String): Flowable<Response<Unit>>

    fun changePassword(email: String, newPassword: String): Flowable<Response<Unit>>

    fun getUserNick(): Flowable<Response<Map<String, String>>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)
}