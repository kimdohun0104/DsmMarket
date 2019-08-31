package com.dsm.data.repository

import com.dsm.data.dataSource.AccountDataSource
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class AccountRepositoryImpl(private val accountDataSource: AccountDataSource) : AccountRepository {
    override fun login(body: Any): Flowable<Response<Map<String, Any>>> =
        accountDataSource.login(body)

    override fun signUp(body: Any): Flowable<Response<Map<String, Int>>> =
        accountDataSource.signUp(body)

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        accountDataSource.refreshToken(refreshToken)

    override fun sendPasswordCode(email: String): Flowable<Response<Unit>> =
        accountDataSource.sendPasswordCode(email)

    override fun passwordCodeConfirm(body: Any): Flowable<Response<Unit>> =
        accountDataSource.passwordCodeConfirm(body)

    override fun changePassword(newPassword: String): Flowable<Response<Unit>> =
        accountDataSource.changePassword(newPassword)

    override fun changePassword(email: String, newPassword: String): Flowable<Response<Unit>> =
        accountDataSource.changePassword(email, newPassword)

    override fun getRemoteUserNick(): Flowable<String> =
        accountDataSource.getRemoteUserNick().map {
            if (it.code() == 200) {
                it.body()!!["nick"]
            } else {
                ""
            }
        }

    override fun changeUserNick(newNick: String): Flowable<Response<Unit>> =
        accountDataSource.changeUserNick(newNick)

    override fun setAccessToken(token: String) =
        accountDataSource.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        accountDataSource.setRefreshToken(token)

    override fun setUserNick(nick: String) =
        accountDataSource.setUserNick(nick)

    override fun getLocalUserNick(): String? =
        accountDataSource.getLocalUserNick()
}