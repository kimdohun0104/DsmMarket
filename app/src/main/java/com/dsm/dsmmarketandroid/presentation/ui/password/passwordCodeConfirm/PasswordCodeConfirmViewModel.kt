package com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.PasswordCodeConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class PasswordCodeConfirmViewModel(
    private val passwordCodeConfirmUseCase: PasswordCodeConfirmUseCase
) : BaseViewModel() {

    val passwordCode = MutableLiveData<String>()

    val isConfirmPasswordCodeEnable: LiveData<Boolean> = Transformations.map(passwordCode) { it != "" }

    val intentChangePassword = MutableLiveData<Int>()
    val finishActivityEvent = SingleLiveEvent<Any>()

    val toastConfirmCodeFailEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun confirmPasswordCode(email: String) {
        addDisposable(
            passwordCodeConfirmUseCase.create(
                hashMapOf(
                    "email" to email,
                    "mailCode" to passwordCode.value!!.toInt()
                )
            ).subscribe({
                intentChangePassword.value = it
                finishActivityEvent.call()
            }, {
                if (it is HttpException) {
                    if (it.code() == 403)
                        toastConfirmCodeFailEvent.call()
                } else toastServerErrorEvent.call()
            })
        )
    }
}