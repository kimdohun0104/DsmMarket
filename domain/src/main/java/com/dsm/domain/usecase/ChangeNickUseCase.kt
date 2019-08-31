package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.account.AccountService
import io.reactivex.Flowable
import retrofit2.Response

class ChangeNickUseCase(private val accountService: AccountService) :
    UseCase<String, Response<Unit>>() {
    override fun create(data: String): Flowable<Response<Unit>> =
        accountService.changeUserNick(data)
}