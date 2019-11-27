package com.dsm.domain.service

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.AccountRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val errorHandler: ErrorHandler
) : AccountService {

    override fun sendTempPassword(email: String): Flowable<Resource<Unit>> =
        accountRepository.sendTempPassword(email).toResource(errorHandler)

}