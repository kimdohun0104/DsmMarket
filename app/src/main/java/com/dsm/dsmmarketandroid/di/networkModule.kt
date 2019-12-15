package com.dsm.dsmmarketandroid.di

import com.dsm.data.remote.Api
import com.dsm.data.remote.token.TokenInterceptor
import com.dsm.domain.error.ErrorHandler
import com.dsm.data.error.ErrorHandlerImpl
import com.dsm.dsmmarketandroid.presentation.BaseApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(get()))
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl((androidContext() as BaseApp).getApiUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(Api::class.java)
    }

    single<ErrorHandler> { ErrorHandlerImpl() }
}