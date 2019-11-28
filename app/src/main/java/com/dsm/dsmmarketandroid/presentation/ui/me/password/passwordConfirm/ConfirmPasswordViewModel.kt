package com.dsm.dsmmarketandroid.presentation.ui.me.password.passwordConfirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
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
                    when (it) {
                        is Resource.Success -> {
                            intentChangePasswordEvent.call()
                            finishActivityEvent.call()
                        }
                        is Resource.Error -> {
                            when (it.error) {
                                is ErrorEntity.Forbidden -> toastEvent.value = R.string.fail_diff_password
                                is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                else -> toastEvent.value = R.string.fail_server_error
                            }
                        }
                    }
                }, {})
        )
    }
}