package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class LoginUseCase(private val accountRepository: AccountRepository) : UseCase<Any, Unit>() {

    override fun create(data: Any): Flowable<Unit> =
        accountRepository.login(data)

}