package com.dsm.data.dataSource

import com.dsm.data.addSchedulers
import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import retrofit2.Response

class AccountDataSourceImpl(
    private val api: Api,
    private val prefHelper: PrefHelper
) : AccountDataSource {

    override fun login(body: Any): Flowable<Response<Map<String, Any>>> =
        api.login(body).addSchedulers()

    override fun signUp(body: Any): Flowable<Response<Map<String, Int>>> =
        api.signUp(body).addSchedulers()

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        api.refreshToken(refreshToken).addSchedulers()

    override fun sendEmail(email: String): Flowable<Response<Unit>> =
        api.sendMail(email).addSchedulers()

    override fun mailConfirm(body: Any): Flowable<Response<Unit>> =
        api.mailConfirm(body).addSchedulers()

    override fun setAccessToken(token: String) =
        prefHelper.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        prefHelper.setRefreshToken(token)
}