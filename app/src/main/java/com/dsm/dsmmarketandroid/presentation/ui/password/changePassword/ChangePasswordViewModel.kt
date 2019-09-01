package com.dsm.dsmmarketandroid.presentation.ui.password.changePassword

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ChangePasswordViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : BaseViewModel() {

    val newPassword = MutableLiveData<String>()
    val reType = MutableLiveData<String>()

    val isChangePasswordEnable = MediatorLiveData<Boolean>().apply {
        addSource(newPassword) { value = !newPassword.value.isNullOrBlank() && !reType.value.isNullOrBlank() }
        addSource(reType) { value = !newPassword.value.isNullOrBlank() && !reType.value.isNullOrBlank() }
    }

    val toastPasswordDiffEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val finishActivityEvent = SingleLiveEvent<Any>()

    val intentLoginActivity = SingleLiveEvent<Any>()


    fun changePassword(email: String) {
        if (newPassword.value != reType.value) {
            toastPasswordDiffEvent.call()
            return
        }

        addDisposable(
            if (email.isBlank()) {
                changePasswordUseCase.create(newPassword.value!!)
                    .subscribe({
                        when (it) {
                            200 -> finishActivityEvent.call()
                            else -> toastServerErrorEvent.call()
                        }
                    }, {
                        toastServerErrorEvent.call()
                    })
            } else {
                changePasswordUseCase.create(ChangePasswordUseCase.Params(email, newPassword.value!!))
                    .subscribe({
                        when (it) {
                            200 -> intentLoginActivity.call()
                            else -> toastServerErrorEvent.call()
                        }
                    }, {
                        toastServerErrorEvent.call()
                    })
            }
        )
    }
}