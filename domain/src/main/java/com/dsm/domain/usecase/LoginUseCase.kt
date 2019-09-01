package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class LoginUseCase(private val accountRepository: AccountRepository) : UseCase<Any, Int>() {

    override fun create(data: Any): Flowable<Int> =
        accountRepository.login(data)

}