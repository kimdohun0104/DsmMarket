package com.dsm.dsmmarketandroid.presentation.ui.changeNick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class ChangeNickViewModel(private val changeNickUseCase: ChangeNickUseCase) : BaseViewModel() {

    val nick = MutableLiveData<String>().apply { value = "" }

    val isChangeNickEnable: LiveData<Boolean> = Transformations.map(nick) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastExistentNickEvent = SingleLiveEvent<Any>()
    val toastServerErrorEvent = SingleLiveEvent<Any>()

    fun changeNick() {
        if (!isChangeNickEnable.value!!) return

        addDisposable(
            changeNickUseCase.create(nick.value!!)
                .subscribe({
                    finishActivityEvent.call()
                }, {
                    if (it is HttpException) {
                        if (it.code() == 403)
                            toastExistentNickEvent.call()
                    } else toastServerErrorEvent.call()
                })
        )
    }
}