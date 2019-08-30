package com.dsm.dsmmarketandroid.presentation.ui.findPassword

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.SendMailUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class FindPasswordViewModel(private val findPasswordUseCase: SendMailUseCase) : BaseViewModel() {

    val email = MutableLiveData<String>()

    val isFindPasswordBtnEnable = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = !email.value.isNullOrBlank() }
    }

    val sendEmailSuccessEvent = SingleLiveEvent<Any>()
    val sendEmailFailEvent = SingleLiveEvent<Any>()

    fun sendEmail() {
        addDisposable(
            findPasswordUseCase.create(email.value!!)
                .subscribe({
                    when (it.code()) {
                        200 -> sendEmailSuccessEvent.call()
                        else -> sendEmailFailEvent.call()
                    }
                }, {
                    sendEmailFailEvent.call()
                })
        )
    }
}