package com.dsm.dsmmarketandroid.presentation.ui.changeNick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ChangeNickViewModel(private val changeNickUseCase: ChangeNickUseCase) : BaseViewModel() {

    val nick = MutableLiveData<String>()

    val isChangeNickEnable: LiveData<Boolean> = Transformations.map(nick) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastExistentNickEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun changeNick() {
        addDisposable(
            changeNickUseCase.create(nick.value!!)
                .subscribe({
                    when (it) {
                        200 -> finishActivityEvent.call()
                        403 -> toastExistentNickEvent.call()
                    }
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}