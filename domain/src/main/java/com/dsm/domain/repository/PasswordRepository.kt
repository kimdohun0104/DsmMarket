package com.dsm.domain.repository

import io.reactivex.Flowable

interface PasswordRepository {
    fun confirmPassword(password: String): Flowable<Pair<String, Int>>

    fun changePassword(params: Any): Flowable<Unit>

    fun sendTempPassword(email: String): Flowable<Unit>
}