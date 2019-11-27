package com.dsm.data.dataSource.account

import io.reactivex.Flowable
import retrofit2.Response

interface AccountDataSource {

    fun sendTempPassword(email: String): Flowable<Unit>

    fun changeUserNick(newNick: String): Flowable<Response<Unit>>
}