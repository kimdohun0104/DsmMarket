package com.dsm.data.dataSource.account

import io.reactivex.Flowable

interface AccountDataSource {

    fun sendTempPassword(email: String): Flowable<Unit>

    fun changePassword(password: String): Flowable<Unit>

    fun changeUserNick(newNick: String): Flowable<Unit>

    fun setLocalUserNick(nick: String)
}