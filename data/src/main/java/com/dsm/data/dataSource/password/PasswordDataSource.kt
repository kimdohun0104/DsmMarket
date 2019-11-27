package com.dsm.data.dataSource.password

import io.reactivex.Flowable
import retrofit2.Response

interface PasswordDataSource {
    fun confirmPassword(password: String): Flowable<Response<Unit>>

    fun changePassword(password: String): Flowable<Response<Unit>>

}