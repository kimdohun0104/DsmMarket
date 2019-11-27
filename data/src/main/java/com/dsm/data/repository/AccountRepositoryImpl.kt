package com.dsm.data.repository

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class AccountRepositoryImpl(
    private val accountDataSource: AccountDataSource,
    private val prefHelper: PrefHelper
) : AccountRepository {

    override fun sendTempPassword(email: String): Flowable<Unit> =
        accountDataSource.sendTempPassword(email)

    override fun changeUserNick(newNick: String): Flowable<Unit> =
        accountDataSource.changeUserNick(newNick).map {
            if (it.code() == 200) prefHelper.setUserNick(newNick)
            else throw HttpException(it)
        }
}