package com.dsm.data.dataSource.password

import io.reactivex.Flowable
import retrofit2.Response

interface PasswordDataSource {
    fun confirmPassword(password: String): Flowable<Response<Map<String, String>>>

    fun sendPasswordCode(email: String): Flowable<Response<Unit>>

    fun passwordCodeConfirm(body: Any): Flowable<Response<Map<String, Int>>>

    fun changePassword(params: Any): Flowable<Response<Unit>>
}