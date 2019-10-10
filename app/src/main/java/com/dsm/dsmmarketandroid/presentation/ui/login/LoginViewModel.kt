package com.dsm.dsmmarketandroid.presentation.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class
LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isLoginEnable = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
        addSource(password) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
        value = false
    }

    val intentMainActivityEvent = SingleLiveEvent<Any>()
    val toastLoginFailEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()
    val hideKeyboardEvent = SingleLiveEvent<Any>()
    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    fun login() {
        if (!isLoginEnable.value!!) return

        showLoadingDialogEvent.call()

        addDisposable(
            loginUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            )
                .subscribe({
                    hideLoadingDialogEvent.call()
                    hideKeyboardEvent.call()
                    intentMainActivityEvent.call()
                }, {
                    hideLoadingDialogEvent.call()
                    if (it is HttpException) {
                        if (it.code() == 403) {
                            toastLoginFailEvent.call()
                        } else toastServerErrorEvent.call()
                    } else toastServerErrorEvent.call()
                })
        )
    }
}