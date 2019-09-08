package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable
import retrofit2.Response

class PasswordCodeConfirmUseCase(private val passwordRepository: PasswordRepository) : UseCase<Any, Response<Map<String, Int>>>() {
    override fun create(data: Any): Flowable<Response<Map<String, Int>>> =
        passwordRepository.passwordCodeConfirm(data)

}