package com.dsm.dsmmarketandroid.presentation.ui.signUp

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import com.dsm.dsmmarketandroid.presentation.util.Validator
import com.dsm.dsmmarketandroid.presentation.util.isValueBlank
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

    val showLoadingDialogEvent = SingleLiveEvent<Any>()

    val finishActivityEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    val toastEvent = SingleLiveEvent<Int>()

    fun signUp() {
        if (!Validator.validEmail(email.value!!)) {
            toastEvent.value = R.string.fail_invalid_email
            return
        }

        if (password.value != reType.value) {
            toastEvent.value = R.string.fail_diff_password
            return
        }

        addDisposable(
            signUpUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value,
                    "nick" to name.value,
                    "grade" to grade.value?.toInt(),
                    "gender" to gender.value
                )
            )
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .subscribe({
                    finishActivityEvent.call()
                }, {
                    if (it is HttpException && it.code() == 403) {
                        val errorResponse = JSONObject(it.response()?.errorBody()?.string()!!)
                        if (errorResponse.has("message")) {
                            if (errorResponse.getString("message") == "existent email")
                                toastEvent.value = R.string.fail_existent_email
                            else
                                toastEvent.value = R.string.fail_existent_nick
                        }
                    } else toastEvent.value = R.string.fail_server_error
                })
        )
    }
}