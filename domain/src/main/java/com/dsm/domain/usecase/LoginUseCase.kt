package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.AuthService
import io.reactivex.Flowable

class LoginUseCase(private val authService: AuthService) : UseCase<Any, Unit>() {

    override fun create(data: Any): Flowable<Unit> =
        authService.login(data)

}