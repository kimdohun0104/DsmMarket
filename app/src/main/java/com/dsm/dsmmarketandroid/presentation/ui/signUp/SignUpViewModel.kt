package com.dsm.dsmmarketandroid.presentation.ui.signUp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator
import org.json.JSONObject
import retrofit2.HttpException

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

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    fun signUp() {
        if (!Validator.validEmail(email.value!!)) {
            toastEmailInvalidEvent.call()
            return
        }

        if (password.value != reType.value) {
            toastPasswordDiffEvent.call()
            return
        }

        showLoadingDialogEvent.call()

        addDisposable(
            signUpUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value,
                    "nick" to name.value,
                    "grade" to grade.value!!.toInt(),
                    "gender" to gender.value
                )
            )
                .subscribe({
                    hideLoadingDialogEvent.call()
                    finishActivityEvent.call()
                }, {
                    hideLoadingDialogEvent.call()
                    if (it is HttpException) {
                        if (it.code() == 403) {
                            val errorResponse = JSONObject(it.response()?.errorBody()?.string()!!)
                            if (errorResponse.has("errorCode")) {
                                if (errorResponse.getInt("errorCode") == 0)
                                    toastExistentEmailEvent.call()
                                else
                                    toastExistentNameEvent.call()
                            }
                        } else toastServerErrorEvent.call()
                    } else toastServerErrorEvent.call()
                })
        )
    }
}