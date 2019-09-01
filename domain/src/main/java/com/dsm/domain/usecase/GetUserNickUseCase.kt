package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class GetUserNickUseCase(private val accountRepository: AccountRepository) :
    UseCase<Unit, String?>() {
    override fun create(data: Unit): Flowable<String?> =
        accountRepository.getUserNick()

}