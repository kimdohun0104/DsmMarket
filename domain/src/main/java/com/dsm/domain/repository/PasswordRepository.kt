package com.dsm.domain.repository

import io.reactivex.Flowable

interface PasswordRepository {
    fun confirmPassword(password: String): Flowable<Unit>

    fun changePassword(password: String): Flowable<Unit>

}