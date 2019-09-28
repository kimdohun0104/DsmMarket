package com.dsm.domain.repository

import io.reactivex.Flowable
import retrofit2.Response

interface AccountRepository {
    fun login(body: Any): Flowable<Unit>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun getUserNick(): Flowable<String?>

    fun changeUserNick(newNick: String): Flowable<Unit>
}