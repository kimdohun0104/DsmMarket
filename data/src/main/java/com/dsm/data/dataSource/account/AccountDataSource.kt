package com.dsm.data.dataSource.account

import io.reactivex.Flowable
import retrofit2.Response

interface AccountDataSource {

    fun login(body: Any): Flowable<Response<Map<String, String>>>

    fun autoLogin(): Flowable<Response<Unit>>

    fun signUp(body: Any): Flowable<Response<Unit>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun getUserNick(): Flowable<Response<Map<String, String>>>

    fun changeUserNick(newNick: String): Flowable<Response<Unit>>
}