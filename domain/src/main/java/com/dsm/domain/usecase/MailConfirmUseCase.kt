package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class MailConfirmUseCase(private val accountRepository: AccountRepository) : UseCase<Any, Response<Unit>>() {
    override fun create(data: Any): Flowable<Response<Unit>> =
        accountRepository.mailConfirm(data)

}