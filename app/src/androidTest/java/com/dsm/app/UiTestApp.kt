package com.dsm.app

import com.dsm.dsmmarketandroid.presentation.BaseApp

open class UiTestApp : BaseApp() {
    override fun getApiUrl(): String {
        return "http://127.0.0.1:8080"
    }
}