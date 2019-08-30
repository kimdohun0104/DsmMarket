package com.dsm.dsmmarketandroid.presentation.ui.mailConfirm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.MailConfirmUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class MailConfirmViewModel(private val mailConfirmUseCase: MailConfirmUseCase) : BaseViewModel() {

    val confirmCode = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    val isConfirmCodeBtnEnable = MediatorLiveData<Boolean>().apply {
        addSource(confirmCode) { value = !confirmCode.value.isNullOrBlank() }
    }

    val confirmCodeSuccessEvent = SingleLiveEvent<Any>()
    val confirmCodeFailEvent = SingleLiveEvent<Any>()

    val invalidConfirmCodeEvent = SingleLiveEvent<Any>()

    fun confirmCode() {
        addDisposable(
            mailConfirmUseCase.create(
                hashMapOf(
                    "email" to email.value,
                    "code" to confirmCode.value
                )
            ).subscribe({
                when (it.code()) {
                    200 -> confirmCodeSuccessEvent.call()
                    403 -> invalidConfirmCodeEvent.call()
                    else -> confirmCodeFailEvent.call()
                }

            }, {
                confirmCodeFailEvent.call()
            })
        )
    }
}