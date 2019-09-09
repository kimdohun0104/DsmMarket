package com.dsm.data.dataSource.password

import com.dsm.data.remote.entity.ConfirmPasswordEntity
import com.dsm.data.remote.entity.PasswordCodeConfirmEntity
import io.reactivex.Flowable
import retrofit2.Response

interface PasswordDataSource {
    fun confirmPassword(password: String): Flowable<Response<ConfirmPasswordEntity>>

    fun sendPasswordCode(email: String): Flowable<Response<Unit>>

    fun passwordCodeConfirm(body: Any): Flowable<Response<PasswordCodeConfirmEntity>>

    fun changePassword(params: Any): Flowable<Response<Unit>>
}