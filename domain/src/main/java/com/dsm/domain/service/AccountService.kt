package com.dsm.domain.service

import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface AccountService {

    fun sendTempPassword(email: String): Flowable<Unit>

    fun changePassword(password: String): Flowable<Unit>

    fun changeNick(nick: String): Flowable<Unit>
}