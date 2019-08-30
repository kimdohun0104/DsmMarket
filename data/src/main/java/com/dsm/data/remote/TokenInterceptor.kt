package com.dsm.data.remote

import android.util.Log
import com.dsm.data.local.pref.PrefHelper
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val prefHelper: PrefHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("DEBUGLOG", "intercept")
        val request =  chain.request().newBuilder().run {
            addHeader("authorization", prefHelper.getAccessToken()!!)
            build()
        }
        return chain.proceed(request)
    }

}