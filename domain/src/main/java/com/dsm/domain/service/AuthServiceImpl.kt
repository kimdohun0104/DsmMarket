package com.dsm.domain.service

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.AuthRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val errorHandler: ErrorHandler
) : AuthService {

    override fun login(body: Any): Flowable<Resource<Unit>> =
        authRepository.login(body)
            .doOnNext {
                authRepository.setAccessToken(it.accessToken)
                authRepository.setRefreshToken(it.refreshToken)
                authRepository.setUserNick(it.nick)
            }
            .map { Unit }
            .toResource(errorHandler)

    override fun autoLogin(): Flowable<Resource<Unit>> =
        authRepository.autoLogin().toResource(errorHandler)

    override fun signUp(body: Any): Flowable<Resource<Unit>> =
        authRepository.signUp(body).toResource(errorHandler)

    override fun confirmPassword(password: String): Flowable<Resource<Unit>> =
        authRepository.confirmPassword(password).toResource(errorHandler)
}