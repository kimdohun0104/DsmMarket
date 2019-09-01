package com.dsm.dsmmarketandroid.presentation.ui.signUp

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

    val isSignUpEnable = MediatorLiveData<Boolean>().apply {
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

    val toastEmailInvalidEvent = SingleLiveEvent<Any>()
    val toastPasswordDiffEvent = SingleLiveEvent<Any>()

    val toastExistentEmailEvent = SingleLiveEvent<Any>()
    val toastExistentNameEvent = SingleLiveEvent<Any>()

    val finishActivityEvent = SingleLiveEvent<Any>()

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun signUp() {
        if (!Validator.validEmail(email.value!!)) {
            toastEmailInvalidEvent.call()
            return
        }

        if (password.value != reType.value) {
            toastPasswordDiffEvent.call()
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
                    200 -> finishActivityEvent.call()
                    403 -> {
                        if (it.body()!!["errorCode"] == 0) {
                            toastExistentEmailEvent.call()
                        } else {
                            toastExistentNameEvent.call()
                        }
                    }
                }
            }, {
                toastServerErrorEvent.call()
            })
        )
    }
}