package com.dsm.data.repository

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class AccountRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val prefHelper: PrefHelper
) : AccountRepository {

    override fun login(body: Any): Flowable<Int> =
        accountDataSource.login(body).map {
            if (it.code() == 200) {
                val response = it.body()!!
                prefHelper.setAccessToken(response["access_token"] ?: error(""))
                prefHelper.setRefreshToken(response["refresh_token"] ?: error(""))
            }
            it.code()
        }

    override fun login(): Flowable<Int> =
        accountDataSource.login().map { it.code() }

    override fun confirmPassword(password: String): Flowable<Response<Map<String, Any>>> =
        accountDataSource.confirmPassword(password)

    override fun signUp(body: Any): Flowable<Response<Map<String, Int>>> =
        accountDataSource.signUp(body)

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        accountDataSource.refreshToken(refreshToken)

    override fun getUserNick(): Flowable<String?> =
        accountDataSource.getUserNick().map {
            if (it.code() == 200) {
                it.body()!!["nick"]
            } else {
                ""
            }
        }
            .doOnNext { prefHelper.setUserNick(it!!) }
            .onErrorReturn { prefHelper.getUserNick() }

    override fun changeUserNick(newNick: String): Flowable<Int> =
        accountDataSource.changeUserNick(newNick).map {
            if (it.code() == 200) prefHelper.setUserNick(newNick)
            it.code()
        }
}