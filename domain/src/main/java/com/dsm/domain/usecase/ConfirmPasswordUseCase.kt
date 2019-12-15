package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.AuthService
import io.reactivex.Flowable

class ConfirmPasswordUseCase(private val authService: AuthService) : UseCase<String, Unit>() {
    override fun create(data: String): Flowable<Unit> =
        authService.confirmPassword(data)
}