package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class GetUserNickUseCase(private val accountRepository: AccountRepository) :
    UseCase<Unit, Response<Map<String, String>>>() {
    override fun create(data: Unit): Flowable<Response<Map<String, String>>> =
        accountRepository.getUserNick()

}