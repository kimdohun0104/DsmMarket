package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class ChangePasswordUseCase(private val accountRepository: AccountRepository) : UseCase<ChangePasswordUseCase.Params, Response<Unit>>() {

    override fun create(data: Params): Flowable<Response<Unit>> =
        accountRepository.changePassword(data.email, data.newPassword)

    fun create(newPassword: String): Flowable<Response<Unit>> =
        accountRepository.changePassword(newPassword)

    data class Params(val email: String, val newPassword: String)
}