package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val reType = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val gender = MutableLiveData<String>()

    val isSignUpBtnEnable = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = !isBlankExist() }
        addSource(password) { value = !isBlankExist() }
        addSource(reType) { value = !isBlankExist() }
        addSource(name) { value = !isBlankExist() }
        addSource(grade) { value = !isBlankExist() }
        addSource(gender) { value = !isBlankExist() }
    }

    private fun isBlankExist() = email.isValueBlank() || password.isValueBlank()
            || reType.isValueBlank() || name.isValueBlank()
            || grade.isValueBlank() || gender.isValueBlank()

    private fun MutableLiveData<String>.isValueBlank() = this.value.isNullOrBlank()

    val emailInvalidEvent = SingleLiveEvent<Any>()
    val passwordDiffEvent = SingleLiveEvent<Any>()

    val signUpSuccessEvent = SingleLiveEvent<Any>()
    val signUpFailEvent = SingleLiveEvent<Any>()

    val existentEmailEvent = SingleLiveEvent<Any>()
    val existentNameEvent = SingleLiveEvent<Any>()

    fun signUp() {
        if (!Validator.validEmail(email.value!!)) {
            emailInvalidEvent.call()
            return
        }

        if (password.value != reType.value) {
            passwordDiffEvent.call()
            return
        }

        addDisposable(
            signUpUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value,
                    "nick" to name.value,
                    "grade" to grade.value,
                    "gender" to gender.value
                )
            ).subscribe({
                when (it.code()) {
                    200 -> signUpSuccessEvent.call()
                    403 -> {
                        if (it.body()!!["errorCode"] == 0) {
                            existentEmailEvent.call()
                        } else {
                            existentNameEvent.call()
                        }
                    }
                }
            }, {
                signUpFailEvent.call()
            })
        )
    }
}