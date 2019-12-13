package com.dsm.dsmmarketandroid.presentation.ui.splash

import com.dsm.domain.usecase.AutoLoginUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashViewModel(private val autoLoginUseCase: AutoLoginUseCase) : BaseViewModel() {

    val intentStartActivity = SingleLiveEvent<Any>()
    val intentMainActivityEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()

    fun login() {
        addDisposable(
            autoLoginUseCase.create(Unit)
                .delay(700, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { finishActivityEvent.call() }
                .subscribe({
                    intentMainActivityEvent.call()
                }, {
                    intentStartActivity.call()
                })
        )
    }
}