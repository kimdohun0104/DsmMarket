package com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.PasswordCodeConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class PasswordCodeConfirmViewModel(
    private val passwordCodeConfirmUseCase: PasswordCodeConfirmUseCase
) : BaseViewModel() {

    val passwordCode = MutableLiveData<String>()

    val isConfirmPasswordCodeEnable: LiveData<Boolean> = Transformations.map(passwordCode) { it != "" }

    val intentChangePasswordWithEmail = MutableLiveData<String>()
    val confirmCodeFailEvent = SingleLiveEvent<Any>()
    val serverErrorEvent = SingleLiveEvent<Any>()

    fun confirmPasswordCode(email: String) {
        addDisposable(
            passwordCodeConfirmUseCase.create(
                hashMapOf(
                    "email" to email,
                    "code" to passwordCode.value!!.toInt()
                )
            ).subscribe({
                when (it.code()) {
                    200 -> intentChangePasswordWithEmail.value = email
                    403 -> confirmCodeFailEvent.call()
                }
            }, {
                serverErrorEvent.call()
            })
        )
    }
}