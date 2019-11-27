package com.dsm.domain.repository

import io.reactivex.Flowable

interface AccountRepository {

    fun sendTempPassword(email: String): Flowable<Unit>

    fun changeUserNick(newNick: String): Flowable<Unit>
}