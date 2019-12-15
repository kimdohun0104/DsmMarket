package com.dsm.dsmmarketandroid.presentation.ui.main.me.changeNick

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import kr.sdusb.libs.messagebus.MessageBus

class ChangeNickViewModel(private val changeNickUseCase: ChangeNickUseCase) : BaseViewModel() {

    val nick = MutableLiveData<String>()

    val isChangeNickEnable: LiveData<Boolean> = Transformations.map(nick) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()
    val toastEvent = SingleLiveEvent<Int>()
    val changeNickLogEvent = SingleLiveEvent<Bundle>()

    fun changeNick() {
        addDisposable(
            changeNickUseCase.create(nick.value!!)
                .doOnNext { changeNickLogEvent.value = Bundle().apply { putString(Analytics.USER_NAME, nick.value) } }
                .subscribe({
                    finishActivityEvent.call()
                    toastEvent.value = R.string.success_change_nick
                    MessageBus.getInstance().handle(MessageEvents.NICK_CHANGED_EVENT, null)
                }, {
                    toastEvent.value = when (it) {
                        is ForbiddenException -> R.string.fail_existent_nick
                        is UnauthorizedException -> R.string.fail_unauthorized
                        else -> R.string.fail_server_error
                    }
                })
        )
    }
}