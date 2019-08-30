package com.dsm.data.remote

import android.content.Context
import android.content.Intent
import android.util.Log
import com.dsm.data.local.pref.PrefHelper
import com.dsm.domain.usecase.RefreshTokenUseCase
import okhttp3.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class TokenAuthenticator(private val prefHelper: PrefHelper) : Authenticator, KoinComponent {

    private val refreshTokenUseCase: RefreshTokenUseCase by inject()

    override fun authenticate(route: Route?, response: Response): Request? {
        Log.d("DEBUGLOG", "authenticate")
        val refreshToken = prefHelper.getRefreshToken()!!
        synchronized(this) {
            if (response.code() == 401) {
                val refreshResponse = refreshTokenUseCase.create(refreshToken).blockingFirst()
                if (refreshResponse.code() == 200) {
                    val accessToken = refreshTokenUseCase.create(refreshToken).blockingFirst().body()!!["access_token"] as String

                    return response.request()
                        .newBuilder()
                        .removeHeader("authorization")
                        .addHeader("authorization", accessToken)
                        .build()
                } else {
                    prefHelper.deleteRefreshToken()
                    prefHelper.deleteAccessToken()
                }
            }
        }

        return null
    }

}