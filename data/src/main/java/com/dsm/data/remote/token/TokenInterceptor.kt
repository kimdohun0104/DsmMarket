package com.dsm.data.remote.token

import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.usecase.RefreshTokenUseCase
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

class TokenInterceptor(private val prefHelper: PrefHelper) : Interceptor, KoinComponent {

    private val refreshTokenUseCase: RefreshTokenUseCase by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().run {
            addHeader("authorization", prefHelper.getAccessToken()!!)
            build()
        }

        val response = chain.proceed(request)


        if (response.code() == 401) {
            val refreshResponse = refreshTokenUseCase.create(prefHelper.getRefreshToken()!!).blockingFirst()

            return if (refreshResponse.code() == 200) {
                val accessToken = refreshResponse.body()!!["access_token"] as String
                prefHelper.setAccessToken(accessToken)
                val newRequest = request.newBuilder().run {
                    removeHeader("authorization")
                    addHeader("authorization", accessToken)
                    build()
                }

                chain.proceed(newRequest)
            } else {
                response
            }
        }
        return response
    }
}