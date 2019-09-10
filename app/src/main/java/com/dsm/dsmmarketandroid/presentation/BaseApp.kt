package com.dsm.dsmmarketandroid.presentation

import android.app.Application
import com.dsm.dsmmarketandroid.di.dataSourceModule
import com.dsm.dsmmarketandroid.di.localModule
import com.dsm.dsmmarketandroid.di.mapperModule
import com.dsm.dsmmarketandroid.di.networkModule
import com.dsm.dsmmarketandroid.di.pagingModule
import com.dsm.dsmmarketandroid.di.repositoryModule
import com.dsm.dsmmarketandroid.di.useCaseModule
import com.dsm.dsmmarketandroid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            modules(
                listOf(
                    dataSourceModule,
                    localModule,
                    mapperModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    pagingModule
                )
            )
        }
    }
}