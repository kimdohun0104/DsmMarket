package com.dsm.data.repository

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.HttpException
import retrofit2.Response

class AccountRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val prefHelper: PrefHelper
) : AccountRepository {

    override fun login(body: Any): Flowable<Unit> =
        accountDataSource.login(body).map {
            if (it.code() == 200) {
                val response = it.body()!!
                prefHelper.setAccessToken(response.accessToken)
                prefHelper.setRefreshToken(response.refreshToken)
            } else throw HttpException(it)
        }

    override fun autoLogin(): Flowable<Unit> =
        accountDataSource.autoLogin().map {
            if (it.code() != 200) throw HttpException(it)
        }

    override fun signUp(body: Any): Flowable<Unit> =
        accountDataSource.signUp(body).map {
            if (it.code() != 200) throw HttpException(it)
        }


    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        accountDataSource.refreshToken(refreshToken)

    override fun getUserNick(): Flowable<String?> =
        accountDataSource.getUserNick().map {
            if (it.code() == 200) it.body()!!["nick"]
            else throw HttpException(it)
        }
            .doOnNext { prefHelper.setUserNick(it!!) }
            .onErrorReturn { prefHelper.getUserNick() }

    override fun changeUserNick(newNick: String): Flowable<Unit> =
        accountDataSource.changeUserNick(newNick).map {
            if (it.code() == 200) prefHelper.setUserNick(newNick)
            else throw HttpException(it)
        }
}