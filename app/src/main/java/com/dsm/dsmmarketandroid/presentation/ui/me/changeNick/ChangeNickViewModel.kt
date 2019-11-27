package com.dsm.dsmmarketandroid.presentation.ui.me.changeNick

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import kr.sdusb.libs.messagebus.MessageBus
import retrofit2.HttpException

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
                    if (it is HttpException && it.code() == 403)
                        toastEvent.value = R.string.fail_existent_nick
                    else
                        toastEvent.value = R.string.fail_server_error
                })
        )
    }
}