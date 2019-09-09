package com.dsm.domain.repository

import io.reactivex.Flowable
import retrofit2.Response

interface AccountRepository {
    fun login(body: Any): Flowable<Unit>

    fun login(): Flowable<Unit>

    fun confirmPassword(password: String): Flowable<Response<Map<String, Any>>>

    fun signUp(body: Any): Flowable<Response<Map<String, Int>>>

    fun refreshToken(refreshToken: String): Flowable<Response<Map<String, Any>>>

    fun getUserNick(): Flowable<String?>

    fun changeUserNick(newNick: String): Flowable<Int>
}