package com.dsm.dsmmarketandroid.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val composite = CompositeDisposable()

    fun addDisposable(disposable: Disposable) =
        composite.add(disposable)

    override fun onCleared() {
        composite.dispose()
        super.onCleared()
    }
}