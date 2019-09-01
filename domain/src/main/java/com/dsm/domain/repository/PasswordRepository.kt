package com.dsm.domain.repository

import io.reactivex.Flowable

interface PasswordRepository {
    fun sendPasswordCode(email: String): Flowable<Int>

    fun passwordCodeConfirm(body: Any): Flowable<Int>

    fun changePassword(newPassword: String): Flowable<Int>

    fun changePassword(email: String, newPassword: String): Flowable<Int>
}