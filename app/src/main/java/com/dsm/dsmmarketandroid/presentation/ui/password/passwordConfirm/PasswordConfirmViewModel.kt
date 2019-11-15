package com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class PasswordConfirmViewModel(private val confirmPasswordUseCase: ConfirmPasswordUseCase) : BaseViewModel() {

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
                    if (it is HttpException && it.code() == 403)
                        toastEvent.value = R.string.fail_diff_password
                    else
                        toastEvent.value = R.string.fail_server_error
                })
        )
    }
}