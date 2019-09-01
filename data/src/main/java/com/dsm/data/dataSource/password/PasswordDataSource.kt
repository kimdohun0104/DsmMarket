package com.dsm.data.dataSource.password

import io.reactivex.Flowable
import retrofit2.Response

interface PasswordDataSource {
    fun sendPasswordCode(email: String): Flowable<Response<Unit>>

    fun passwordCodeConfirm(body: Any): Flowable<Response<Unit>>

    fun changePassword(newPassword: String): Flowable<Response<Unit>>

    fun changePassword(email: String, newPassword: String): Flowable<Response<Unit>>
}