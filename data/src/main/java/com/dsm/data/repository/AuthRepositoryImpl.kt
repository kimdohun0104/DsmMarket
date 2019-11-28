package com.dsm.data.repository

import com.dsm.data.dataSource.auth.AuthDataSource
import com.dsm.data.mapper.TokenMapper
import com.dsm.domain.entity.Token
import com.dsm.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.Response

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

    override fun confirmPassword(password: String): Flowable<Unit> =
        dataSource.confirmPassword(password)

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        dataSource.refreshToken(refreshToken)

    override fun setAccessToken(token: String) =
        dataSource.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        dataSource.setRefreshToken(token)

    override fun setUserNick(nick: String) =
        dataSource.setUserNick(nick)
}