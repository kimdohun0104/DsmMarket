package com.dsm.data.local.pref

import android.content.Context

class PrefHelperImpl(context: Context) : PrefHelper {

    companion object {
        private const val PREF_NAME = "com.dsm.dsmmarketandroid"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val USER_NICK = "USER_NICK"
    }

    private val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun setAccessToken(accessToken: String) = pref.edit().putString(ACCESS_TOKEN, accessToken).apply()

    override fun setRefreshToken(refreshToken: String) = pref.edit().putString(REFRESH_TOKEN, refreshToken).apply()

    override fun getAccessToken(): String? = pref.getString(ACCESS_TOKEN, "")

    override fun getRefreshToken(): String? = pref.getString(REFRESH_TOKEN, "")

    override fun deleteAccessToken() = pref.edit().putString(ACCESS_TOKEN, "").apply()

    override fun deleteRefreshToken() = pref.edit().putString(REFRESH_TOKEN, "").apply()

    override fun setUserNick(nick: String) = pref.edit().putString(USER_NICK, nick).apply()

    override fun getUserNick(): String? = pref.getString(USER_NICK, "")

    override fun deleteUserNick() = pref.edit().putString(USER_NICK, "").apply()
}