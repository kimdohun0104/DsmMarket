package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class AutoLoginUseCase(private val accountRepository: AccountRepository) : UseCase<Unit, Unit>() {
    override fun create(data: Unit): Flowable<Unit> =
        accountRepository.autoLogin()
}