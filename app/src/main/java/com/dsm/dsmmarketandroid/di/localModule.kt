package com.dsm.dsmmarketandroid.di

import androidx.room.Room
import com.dsm.data.local.db.AppDatabase
import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.local.pref.PrefHelperImpl
import org.koin.dsl.module

val localModule = module {
    single<PrefHelper> { PrefHelperImpl(get()) }

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "dsmmarket.db").allowMainThreadQueries().build() }

    factory { get<AppDatabase>().purchaseDao() }

    factory { get<AppDatabase>().rentDao() }

    factory { get<AppDatabase>().searchDao() }
}