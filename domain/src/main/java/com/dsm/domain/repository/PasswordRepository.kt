package com.dsm.domain.repository

import io.reactivex.Flowable

interface PasswordRepository {
    fun confirmPassword(password: String): Flowable<Pair<String, Int>>

    fun sendPasswordCode(email: String): Flowable<Unit>

    fun passwordCodeConfirm(body: Any): Flowable<Int>

    fun changePassword(params: Any): Flowable<Unit>
}