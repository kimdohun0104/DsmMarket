package com.dsm.domain.repository

import com.dsm.domain.entity.Token
import io.reactivex.Flowable
import retrofit2.Response

interface AuthRepository {

    fun login(body: Any): Flowable<Token>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun confirmPassword(password: String): Flowable<Unit>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setUserNick(nick: String)
}