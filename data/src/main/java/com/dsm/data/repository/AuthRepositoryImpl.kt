package com.dsm.data.repository

import com.dsm.data.dataSource.auth.AuthDataSource
import com.dsm.data.mapper.TokenMapper
import com.dsm.domain.entity.Token
import com.dsm.domain.repository.AuthRepository
import io.reactivex.Flowable

class AuthRepositoryImpl(
    private val dataSource: AuthDataSource,
    private val tokenMapper: TokenMapper
) : AuthRepository {

    override fun login(body: Any): Flowable<Token> =
        dataSource.login(body).map(tokenMapper::mapFrom)

    override fun autoLogin(): Flowable<Unit> =
        dataSource.autoLogin()

    override fun signUp(body: Any): Flowable<Unit> =
        dataSource.signUp(body)

    override fun setAccessToken(token: String) =
        dataSource.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        dataSource.setRefreshToken(token)

    override fun setUserNick(nick: String) =
        dataSource.setUserNick(nick)
}