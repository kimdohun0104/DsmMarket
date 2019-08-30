package com.dsm.data.dataSource

import android.content.SharedPreferences
import com.dsm.data.addSchedulers
import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class AccountDataSourceImpl(
    private val api: Api,
    private val prefHelper: PrefHelper
) : AccountDataSource {

    override fun login(body: Any): Flowable<Response<Map<String, Any>>> =
        api.login(body).addSchedulers()

    override fun signUp(body: Any): Flowable<Response<Map<String, Int>>> =
        api.join(body).addSchedulers()

    override fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>> =
        api.refreshToken(refreshToken).addSchedulers()

    override fun setAccessToken(token: String) =
        prefHelper.setAccessToken(token)

    override fun setRefreshToken(token: String) =
        prefHelper.setRefreshToken(token)
}