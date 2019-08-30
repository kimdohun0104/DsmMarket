package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class SignUpUseCase(private val accountRepository: AccountRepository) : UseCase<Any, Response<Map<String, Int>>>() {
    override fun create(data: Any): Flowable<Response<Map<String, Int>>> =
        accountRepository.signUp(data)

}