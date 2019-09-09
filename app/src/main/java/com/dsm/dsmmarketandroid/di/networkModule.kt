package com.dsm.dsmmarketandroid.di

import com.dsm.data.remote.Api
import com.dsm.data.remote.token.TokenInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.10:1234/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(Api::class.java)
    }
}