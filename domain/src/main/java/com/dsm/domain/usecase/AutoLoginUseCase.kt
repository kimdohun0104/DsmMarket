package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.AuthService
import io.reactivex.Flowable

class AutoLoginUseCase(private val authService: AuthService) : UseCase<Unit, Resource<Unit>>() {
    override fun create(data: Unit): Flowable<Resource<Unit>> =
        authService.autoLogin()
}