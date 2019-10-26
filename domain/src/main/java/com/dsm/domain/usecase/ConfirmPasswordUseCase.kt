package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class ConfirmPasswordUseCase(private val passwordRepository: PasswordRepository) : UseCase<String, Unit>() {
    override fun create(data: String): Flowable<Unit> =
        passwordRepository.confirmPassword(data)
}