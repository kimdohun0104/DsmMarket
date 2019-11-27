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

}