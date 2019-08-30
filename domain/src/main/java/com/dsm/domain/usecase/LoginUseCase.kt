package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.account.AccountService
import io.reactivex.Flowable
import retrofit2.Response

class LoginUseCase(private val accountService: AccountService) : UseCase<Any, Response<Map<String, Any>>>() {

    override fun create(data: Any): Flowable<Response<Map<String, Any>>> =
        accountService.login(data)

}