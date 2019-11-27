package com.dsm.data.dataSource.account

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import retrofit2.Response

class AccountDataSourceImpl(private val api: Api) : AccountDataSource {

    override fun sendTempPassword(email: String): Flowable<Unit> =
        api.sendTempPassword(email).addSchedulers()

    override fun changeUserNick(newNick: String): Flowable<Response<Unit>> =
        api.changeUserNick(newNick).addSchedulers()
}