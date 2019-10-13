package com.dsm.dsmmarketandroid.di

import com.dsm.data.remote.Api
import com.dsm.data.remote.token.TokenInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(get()))
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://52.78.148.203/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(Api::class.java)
    }
}