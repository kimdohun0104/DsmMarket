package com.dsm.data.dataSource.auth

import com.dsm.data.remote.entity.TokenEntity
import io.reactivex.Flowable

interface AuthDataSource {

    fun login(body: Any): Flowable<TokenEntity>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun setAccessToken(token: String)

    fun setRefreshToken(token: String)

    fun setUserNick(nick: String)
}