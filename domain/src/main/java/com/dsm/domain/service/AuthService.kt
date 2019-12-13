package com.dsm.domain.service

import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface AuthService {

    fun login(body: Any) : Flowable<Unit>

    fun autoLogin(): Flowable<Unit>

    fun signUp(body: Any): Flowable<Unit>

    fun confirmPassword(password: String): Flowable<Unit>
}