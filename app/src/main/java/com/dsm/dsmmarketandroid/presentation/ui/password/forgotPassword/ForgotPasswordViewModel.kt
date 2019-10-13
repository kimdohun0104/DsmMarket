package com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.SendTempPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ForgotPasswordViewModel(private val sendTempPasswordUseCase: SendTempPasswordUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>().apply { value = "" }

    val isSendTempPasswordEnable = Transformations.map(email) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastSendTempPasswordSuccess = SingleLiveEvent<Any>()
    val toastServerError = SingleLiveEvent<Any>()

    fun sendTempPassword() {
        if (!isSendTempPasswordEnable.value!!) {
            return
        }

        addDisposable(
            sendTempPasswordUseCase.create(email.value!!)
                .subscribe({
                    toastSendTempPasswordSuccess.call()
                    finishActivityEvent.call()
                }, {
                    toastServerError.call()
                })
        )
    }
}