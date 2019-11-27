package com.dsm.dsmmarketandroid.presentation.ui.auth.login

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.LoginUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
import retrofit2.HttpException

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isLoginEnable = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(email) { value = !email.isValueBlank() && !password.isValueBlank() }
        addSource(password) { value = !email.isValueBlank() && !password.isValueBlank() }
    }

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()
    val intentMainActivityEvent = SingleLiveEvent<Any>()
    val hideKeyboardEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()
    val loginLogEvent = SingleLiveEvent<Bundle>()

    fun login() {
        if (!Validator.validEmail(email.value!!)) {
            toastEvent.value = R.string.fail_invalid_email
            return
        }

        addDisposable(
            loginUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            )
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .doOnNext { loginLogEvent.value = Bundle().apply { putString(Analytics.USER_EMAIL, email.value) } }
                .subscribe({
                    hideLoadingDialogEvent.call()
                    hideKeyboardEvent.call()
                    intentMainActivityEvent.call()
                }, {
                    if (it is HttpException && it.code() == 403)
                        toastEvent.value = R.string.fail_login
                    else
                        toastEvent.value = R.string.fail_server_error
                })
        )
    }
}