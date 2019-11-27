package com.dsm.domain.repository

import com.dsm.domain.entity.Token
import io.reactivex.Flowable

interface AuthRepository {

    fun login(body: Any): Flowable<Token>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setUserNick(nick: String)
}