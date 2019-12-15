package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.AuthService
import io.reactivex.Flowable

class SignUpUseCase(private val authService: AuthService) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        authService.signUp(data)

}