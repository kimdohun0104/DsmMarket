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

    override fun sendMail(email: String): Flowable<Response<Unit>> =
        accountDataSource.sendEmail(email)

    override fun mailConfirm(body: Any): Flowable<Response<Unit>> =
        accountDataSource.mailConfirm(body)

    override fun setAccessToken(token: String) =
        accountDataSource.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        accountDataSource.setRefreshToken(token)
}