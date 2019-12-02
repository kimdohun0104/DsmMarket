package com.dsm.domain.service

import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface AccountService {

    fun sendTempPassword(email: String): Flowable<Resource<Unit>>

    fun changePassword(password: String): Flowable<Resource<Unit>>

    fun changeNick(nick: String): Flowable<Resource<Unit>>
}