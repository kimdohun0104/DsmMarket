package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class ChangePasswordUseCase(private val passwordRepository: PasswordRepository) : UseCase<Any, Int>() {
    override fun create(data: Any): Flowable<Int> =
        passwordRepository.changePassword(data)

}