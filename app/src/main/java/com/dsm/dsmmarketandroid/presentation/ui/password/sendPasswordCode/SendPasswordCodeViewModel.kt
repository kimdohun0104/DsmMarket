package com.dsm.dsmmarketandroid.presentation.ui.password.sendPasswordCode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.SendPasswordCodeUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator

class SendPasswordCodeViewModel(
    private val sendPasswordCodeUseCase: SendPasswordCodeUseCase
) : BaseViewModel() {

    val email = MutableLiveData<String>()

    val isSendPasswordCodeEnable: LiveData<Boolean> = Transformations.map(email) { it != "" }

    val toastInvalidEmailEvent = SingleLiveEvent<Any>()
    val intentPasswordCodeConfirmWithEmail = MutableLiveData<String>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun sendPasswordCode() {
        if (!Validator.validEmail(email.value!!)) {
            toastInvalidEmailEvent.call()
            return
        }

        addDisposable(
            sendPasswordCodeUseCase.create(email.value!!)
                .subscribe({
                    when (it) {
                        200 -> intentPasswordCodeConfirmWithEmail.value = email.value
                        else -> toastServerErrorEvent.call()
                    }
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}