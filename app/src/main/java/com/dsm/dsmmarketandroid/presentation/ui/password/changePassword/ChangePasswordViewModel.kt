package com.dsm.dsmmarketandroid.presentation.ui.password.changePassword

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : BaseViewModel() {

    val newPassword = MutableLiveData<String>()
    val reType = MutableLiveData<String>()

    val isChangePasswordEnable = MediatorLiveData<Boolean>().apply {
        addSource(newPassword) { value = !newPassword.isValueBlank() && !reType.isValueBlank() }
        addSource(reType) { value = !newPassword.isValueBlank()&& !reType.isValueBlank() }
        value = false
    }

    val toastPasswordDiffEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val finishActivityEvent = SingleLiveEvent<Any>()

    fun changePassword(email: String, authCode: Int) {
        if (!isChangePasswordEnable.value!!) return

        if (newPassword.value != reType.value) {
            toastPasswordDiffEvent.call()
            return
        }

        addDisposable(
            changePasswordUseCase.create(
                mapOf(
                    "email" to email,
                    "authCode" to authCode,
                    "password" to newPassword.value
                )
            ).subscribe({
                finishActivityEvent.call()
            }, {
                toastServerErrorEvent.call()
            })
        )
    }
}