package com.dsm.dsmmarketandroid.di

import com.dsm.data.local.pref.PrefHelper
import com.dsm.data.local.pref.PrefHelperImpl
import org.koin.dsl.module

val localModule = module {
    single<PrefHelper> { PrefHelperImpl(get()) }
}