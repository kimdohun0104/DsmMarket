package com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ConfirmPasswordUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class PasswordConfirmViewModel(private val confirmPasswordUseCase: ConfirmPasswordUseCase) : BaseViewModel() {

    val originalPassword = MutableLiveData<String>().apply { value = "" }

    val isConfirmEnable = Transformations.map(originalPassword) { it != "" }

    val intentChangePassword = MutableLiveData<Pair<String, Int>>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastInvalidPasswordEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun confirmPassword() {
        if (!isConfirmEnable.value!!) return

        addDisposable(
            confirmPasswordUseCase.create(originalPassword.value!!)
                .subscribe({
                    intentChangePassword.value = it
                    finishActivityEvent.call()
                }, {
                    if (it is HttpException) {
                        if (it.code() == 2)
                            toastInvalidPasswordEvent.call()
                    }
                    toastServerErrorEvent.call()
                })
        )
    }
}