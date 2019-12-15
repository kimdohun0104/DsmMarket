package com.dsm.dsmmarketandroid.presentation.ui.main.me.password.passwordConfirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ConfirmPasswordViewModel(private val confirmPasswordUseCase: ConfirmPasswordUseCase) : BaseViewModel() {

    val originalPassword = MutableLiveData<String>()

    val isConfirmEnable: LiveData<Boolean> = Transformations.map(originalPassword) { it != "" }

    val intentChangePasswordEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()

    fun confirmPassword() {
        addDisposable(
            confirmPasswordUseCase.create(originalPassword.value!!)
                .subscribe({
                    intentChangePasswordEvent.call()
                    finishActivityEvent.call()
                }, {
                    toastEvent.value = when (it) {
                        is ForbiddenException -> R.string.fail_diff_password
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }
}