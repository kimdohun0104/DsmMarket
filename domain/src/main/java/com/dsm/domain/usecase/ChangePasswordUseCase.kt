package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class ChangePasswordUseCase(private val passwordRepository: PasswordRepository) : UseCase<ChangePasswordUseCase.Params, Int>() {

    override fun create(data: Params): Flowable<Int> =
        passwordRepository.changePassword(data.email, data.newPassword)

    fun create(newPassword: String): Flowable<Int> =
        passwordRepository.changePassword(newPassword)

    data class Params(val email: String, val newPassword: String)
}