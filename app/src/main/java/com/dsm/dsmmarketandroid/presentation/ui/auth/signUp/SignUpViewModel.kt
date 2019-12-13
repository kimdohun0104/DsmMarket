package com.dsm.dsmmarketandroid.presentation.ui.auth.signUp

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.InternalException
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
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

    val pagerIndex = MutableLiveData<Int>()

    val btnText: LiveData<Int> = Transformations.map(pagerIndex) { if (it == 0) R.string.next else R.string.sign_up }

    val isBtnEnable = MediatorLiveData<Boolean>().apply {
        addSource(pagerIndex) {
            value =
                if (it == 0) !signUp1Enable()
                else !isBlankExist()
        }
        addSource(email) { value = !signUp1Enable() }
        addSource(password) { value = !signUp1Enable() }
        addSource(reType) { value = !signUp1Enable() }
        addSource(name) { value = !isBlankExist() }
        addSource(grade) { value = !isBlankExist() }
        addSource(gender) { value = !isBlankExist() }
    }

    private fun signUp1Enable() = email.isValueBlank() || password.isValueBlank() || reType.isValueBlank()

    private fun isBlankExist() = email.isValueBlank() || password.isValueBlank()
            || reType.isValueBlank() || name.isValueBlank()
            || grade.isValueBlank() || gender.isValueBlank()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()
    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()
    val signUpLogEvent = SingleLiveEvent<Bundle>()

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
                .doOnComplete {
                    signUpLogEvent.value = Bundle().apply {
                        putString(Analytics.USER_NAME, name.value)
                        putString(Analytics.USER_GRADE, grade.value)
                        putString(Analytics.USER_EMAIL, email.value)
                    }
                }
                .subscribe({
                    finishActivityEvent.call()
                }, {
                    toastEvent.value = when (it) {
                        is ForbiddenException -> {
                            val response = JSONObject((it as HttpException).response()?.errorBody()?.toString() ?: "{}")
                            if (response.getString("message") == "existent email")
                                R.string.fail_existent_email
                            else
                                R.string.fail_existent_nick
                        }
                        else -> R.string.fail_server_error
                    }
                })
        )
    }
}