package com.dsm.domain.service

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val errorHandler: ErrorHandler
) : AccountService {

    override fun sendTempPassword(email: String): Flowable<Unit> =
        accountRepository.sendTempPassword(email).handleError(errorHandler)

    override fun changePassword(password: String): Flowable<Unit> =
        accountRepository.changePassword(password).handleError(errorHandler)

    override fun changeNick(nick: String): Flowable<Unit> =
        accountRepository.changeUserNick(nick)
            .doOnNext { accountRepository.setLocalUserNick(nick) }
            .handleError(errorHandler)
}