package com.dsm.data.dataSource.account

import com.dsm.data.addSchedulers
import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.remote.Api
import io.reactivex.Flowable

class AccountDataSourceImpl(
    private val api: Api,
    private val prefHelper: PrefHelper
) : AccountDataSource {

    override fun sendTempPassword(email: String): Flowable<Unit> =
        api.sendTempPassword(email).addSchedulers()

    override fun changePassword(password: String): Flowable<Unit> =
        api.changePassword(password).addSchedulers()

    override fun changeUserNick(newNick: String): Flowable<Unit> =
        api.changeUserNick(newNick).addSchedulers()

    override fun setLocalUserNick(nick: String) =
        prefHelper.setUserNick(nick)
}