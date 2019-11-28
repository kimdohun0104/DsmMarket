package com.dsm.data.repository

import com.dsm.data.dataSource.account.AccountDataSource
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class AccountRepositoryImpl(private val accountDataSource: AccountDataSource) : AccountRepository {

    override fun sendTempPassword(email: String): Flowable<Unit> =
        accountDataSource.sendTempPassword(email)

    override fun changePassword(password: String): Flowable<Unit> =
        accountDataSource.changePassword(password)

    override fun changeUserNick(newNick: String): Flowable<Unit> =
        accountDataSource.changeUserNick(newNick)

    override fun setLocalUserNick(nick: String) =
        accountDataSource.setLocalUserNick(nick)
}