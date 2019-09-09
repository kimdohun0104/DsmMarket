package com.dsm.dsmmarketandroid.presentation.ui.splash

import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class SplashViewModel(private val autoLoginUseCase: AutoLoginUseCase) : BaseViewModel() {

    val intentMainActivityEvent = SingleLiveEvent<Any>()
    val intentStartActivity = SingleLiveEvent<Any>()

    fun login() {
        addDisposable(
            autoLoginUseCase.create(Unit).subscribe({
                intentMainActivityEvent.call()
            }, {
                intentStartActivity.call()
            })
        )
    }
}