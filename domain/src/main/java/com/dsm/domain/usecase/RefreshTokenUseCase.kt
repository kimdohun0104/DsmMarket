package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.AuthRepository
import io.reactivex.Flowable
import retrofit2.Response

class RefreshTokenUseCase(private val authRepository: AuthRepository) : UseCase<String, Response<Map<String, Any>>>() {
    override fun create(data: String): Flowable<Response<Map<String, Any>>> =
        authRepository.refreshToken(data)

}