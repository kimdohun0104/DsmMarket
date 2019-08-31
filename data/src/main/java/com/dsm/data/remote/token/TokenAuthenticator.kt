package com.dsm.data.remote.token

import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.usecase.RefreshTokenUseCase
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.KoinComponent
import org.koin.core.inject

class TokenAuthenticator(private val prefHelper: PrefHelper) : Authenticator, KoinComponent {

    private val refreshTokenUseCase: RefreshTokenUseCase by inject()

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = prefHelper.getRefreshToken()!!
        synchronized(this) {
            if (response.code() == 401) {
                val refreshResponse = refreshTokenUseCase.create(refreshToken).blockingFirst()
                if (refreshResponse.code() == 200) {
                    val accessToken = refreshTokenUseCase.create(refreshToken).blockingFirst().body()!!["access_token"] as String
                    prefHelper.setAccessToken(accessToken)

                    return response.request()
                        .newBuilder()
                        .removeHeader("authorization")
                        .addHeader("authorization", accessToken)
                        .build()
                } else {
                    prefHelper.deleteRefreshToken()
                    prefHelper.deleteAccessToken()
                    prefHelper.deleteUserNick()
                }
            }
        }

        return null
    }

}