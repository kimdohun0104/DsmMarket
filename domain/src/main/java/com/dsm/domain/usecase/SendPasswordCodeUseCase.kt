package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class SendPasswordCodeUseCase(private val passwordRepository: PasswordRepository) : UseCase<String, Int>() {
    override fun create(data: String): Flowable<Int> =
        passwordRepository.sendPasswordCode(data)
}