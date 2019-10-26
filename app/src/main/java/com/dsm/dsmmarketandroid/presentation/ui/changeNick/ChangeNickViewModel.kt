package com.dsm.dsmmarketandroid.presentation.ui.changeNick

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import retrofit2.HttpException

class ChangeNickViewModel(private val changeNickUseCase: ChangeNickUseCase) : BaseViewModel() {

    val nick = MutableLiveData<String>()

    val isChangeNickEnable: LiveData<Boolean> = Transformations.map(nick) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()

    fun changeNick() {
        addDisposable(
            changeNickUseCase.create(nick.value!!)
                .subscribe({
                    finishActivityEvent.call()
                    toastEvent.value = R.string.success_change_nick
                }, {
                    if (it is HttpException && it.code() == 403)
                        toastEvent.value = R.string.fail_existent_nick
                    else
                        toastEvent.value = R.string.fail_server_error
                })
        )
    }
}