package com.dsm.domain.service

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.AuthRepository
import io.reactivex.Flowable

class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val errorHandler: ErrorHandler
) : AuthService {

    override fun login(body: Any): Flowable<Unit> =
        authRepository.login(body)
            .doOnNext {
                authRepository.setAccessToken(it.accessToken)
                authRepository.setRefreshToken(it.refreshToken)
                authRepository.setUserNick(it.nick)
            }
            .map { Unit }
            .handleError(errorHandler)

    override fun autoLogin(): Flowable<Unit> =
        authRepository.autoLogin().handleError(errorHandler)

    override fun signUp(body: Any): Flowable<Unit> =
        authRepository.signUp(body).handleError(errorHandler)

    override fun confirmPassword(password: String): Flowable<Unit> =
        authRepository.confirmPassword(password).handleError(errorHandler)
}