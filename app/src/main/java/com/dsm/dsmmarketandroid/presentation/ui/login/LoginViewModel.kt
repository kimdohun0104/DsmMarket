package com.dsm.dsmmarketandroid.presentation.ui.login

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isLoginBtnEnable = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
        addSource(password) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
    }

    val loginSuccessEvent = SingleLiveEvent<Any>()
    val loginFailEvent = SingleLiveEvent<Any>()

    fun login() {
        addDisposable(
            loginUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            ).subscribe({
                when (it.code()) {
                    200 -> loginSuccessEvent.call()
                    403 -> loginFailEvent.call()
                }
            }, {
                loginFailEvent.call()
            })
        )
    }
}