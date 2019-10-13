package com.dsm.data.dataSource.password

import io.reactivex.Flowable
import retrofit2.Response

interface PasswordDataSource {
    fun confirmPassword(password: String): Flowable<Response<Map<String, String>>>

    fun changePassword(params: Any): Flowable<Response<Unit>>

    fun sendTempPassword(email: String): Flowable<Response<Unit>>
}