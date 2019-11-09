package com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.SendTempPasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator

class ForgotPasswordViewModel(private val sendTempPasswordUseCase: SendTempPasswordUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>().apply { value = "" }

    val isSendTempPasswordEnable: LiveData<Boolean> = Transformations.map(email) { it != "" }

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()

    fun sendTempPassword() {
        if (!Validator.validEmail(email.value!!)) {
            toastEvent.value = R.string.fail_invalid_email
            return
        }

        addDisposable(
            sendTempPasswordUseCase.create(email.value!!)
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .subscribe({
                    toastEvent.value = R.string.send_temp_password_success
                    finishActivityEvent.call()
                }, {
                    toastEvent.value = R.string.fail_server_error
                })
        )
    }
}