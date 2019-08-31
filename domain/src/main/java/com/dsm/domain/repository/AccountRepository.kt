package com.dsm.domain.repository

import io.reactivex.Flowable
import retrofit2.Response

interface AccountRepository {
    fun login(body: Any) : Flowable<Response<Map<String, Any>>>

    fun signUp(body: Any) : Flowable<Response<Map<String, Int>>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun sendPasswordCode(email: String): Flowable<Response<Unit>>

    fun passwordCodeConfirm(body: Any): Flowable<Response<Unit>>

    fun changePassword(newPassword: String): Flowable<Response<Unit>>

    fun changePassword(email: String, newPassword: String): Flowable<Response<Unit>>

    fun getRemoteUserNick(): Flowable<String>

    fun changeUserNick(newNick: String): Flowable<Response<Unit>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setUserNick(nick: String)

    fun getLocalUserNick(): String?
}