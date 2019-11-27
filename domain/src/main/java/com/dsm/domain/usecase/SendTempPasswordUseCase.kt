package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.AccountService
import io.reactivex.Flowable

class SendTempPasswordUseCase(private val accountService: AccountService) : UseCase<String, Resource<Unit>>() {
    override fun create(data: String): Flowable<Resource<Unit>> =
        accountService.sendTempPassword(data)

}