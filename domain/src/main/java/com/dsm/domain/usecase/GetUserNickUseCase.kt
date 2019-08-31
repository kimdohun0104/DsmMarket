package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.account.AccountService
import io.reactivex.Flowable

class GetUserNickUseCase(private val accountService: AccountService) :
    UseCase<Unit, String>() {
    override fun create(data: Unit): Flowable<String> =
        accountService.getUserNick()

}