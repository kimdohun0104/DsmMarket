package com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class PasswordConfirmViewModel(private val confirmPasswordUseCase: ConfirmPasswordUseCase) : BaseViewModel() {

    val originalPassword = MutableLiveData<String>()

    val isConfirmEnable = Transformations.map(originalPassword) { it != "" }

    val intentChangePassword = MutableLiveData<Int>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastInvalidPasswordEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun confirmPassword() {
        addDisposable(
            confirmPasswordUseCase.create(originalPassword.value!!)
                .subscribe({
                    when (it.code()) {
                        200 -> {
                            intentChangePassword.value = it.body()!!["authCode"]
                            finishActivityEvent.call()
                        }
                        2 -> toastInvalidPasswordEvent
                    }
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}