package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable

class ChangeNickUseCase(private val accountRepository: AccountRepository) : UseCase<String, Unit>() {
    override fun create(data: String): Flowable<Unit> =
        accountRepository.changeUserNick(data)
}