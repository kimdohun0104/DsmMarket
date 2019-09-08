package com.dsm.domain.repository

import io.reactivex.Flowable
import retrofit2.Response

interface PasswordRepository {
    fun sendPasswordCode(email: String): Flowable<Int>

    fun passwordCodeConfirm(body: Any): Flowable<Response<Map<String, Int>>>

    fun changePassword(params: Any): Flowable<Int>
}