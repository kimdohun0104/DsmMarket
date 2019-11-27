package com.dsm.data.dataSource.auth

import com.dsm.data.remote.entity.TokenEntity
import io.reactivex.Flowable
import retrofit2.Response

interface AuthDataSource {

    fun login(body: Any): Flowable<TokenEntity>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setUserNick(nick: String)
}