package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class ConfirmPasswordUseCase(private val accountRepository: AccountRepository) : UseCase<String, Response<Map<String, Int>>>() {
    override fun create(data: String): Flowable<Response<Map<String, Int>>> =
        accountRepository.confirmPassword(data)
}