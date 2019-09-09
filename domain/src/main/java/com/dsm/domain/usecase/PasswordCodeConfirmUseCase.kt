package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class PasswordCodeConfirmUseCase(private val passwordRepository: PasswordRepository) : UseCase<Any, Int>() {
    override fun create(data: Any): Flowable<Int> =
        passwordRepository.passwordCodeConfirm(data)

}