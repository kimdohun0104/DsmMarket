package com.dsm.data.local.pref

interface PrefHelper {
    fun setAccessToken(accessToken: String)

    fun setRefreshToken(refreshToken: String)

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun deleteAccessToken()

    fun deleteRefreshToken()
}