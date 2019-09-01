package com.dsm.dsmmarketandroid.presentation.ui.splash

import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class SplashViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val intentMainActivityEvent = SingleLiveEvent<Any>()
    val intentLoginActivityEvent = SingleLiveEvent<Any>()

    fun login() {
        addDisposable(
            loginUseCase.create().subscribe({
                when (it) {
                    200 -> intentMainActivityEvent.call()
                    else -> intentLoginActivityEvent.call()
                }
            }, {
                intentLoginActivityEvent.call()
            })
        )
    }
}