package com.dsm.domain.repository

import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response

interface AccountRepository {
    fun login(body: Any) : Flowable<Response<Map<String, Any>>>

    fun signUp(body: Any) : Flowable<Response<Map<String, Int>>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)
}