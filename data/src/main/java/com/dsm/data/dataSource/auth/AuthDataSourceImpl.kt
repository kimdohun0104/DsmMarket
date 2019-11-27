package com.dsm.data.dataSource.auth

import com.dsm.data.addSchedulers
import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.remote.Api
import com.dsm.data.remote.entity.TokenEntity
import io.reactivex.Flowable
import retrofit2.Response

class AuthDataSourceImpl(
    private val api: Api,
    private val prefHelper: PrefHelper
) : AuthDataSource {

    override fun login(body: Any): Flowable<TokenEntity> =
        api.login(body).addSchedulers()

    override fun autoLogin(): Flowable<Unit> =
        api.autoLogin().addSchedulers()

    override fun signUp(body: Any): Flowable<Unit> =
        api.signUp(body).addSchedulers()

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        api.refreshToken(refreshToken).addSchedulers()

    override fun setAccessToken(token: String) =
        prefHelper.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        prefHelper.setRefreshToken(token)

    override fun setUserNick(nick: String) =
        prefHelper.setUserNick(nick)
}