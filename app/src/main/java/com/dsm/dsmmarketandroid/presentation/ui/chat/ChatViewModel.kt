package com.dsm.dsmmarketandroid.presentation.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetChatLogUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ChatModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ChatModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ChatViewModel(
    private val getChatLogUseCase: GetChatLogUseCase,
    private val chatModelMapper: ChatModelMapper
) : BaseViewModel() {

    val newItems = MutableLiveData<List<ChatModel>>()

    val showLoadingItemEvent = SingleLiveEvent<Any>()
    val hideLoadingItemEvent = SingleLiveEvent<Any>()

    fun loadChatLog(roomId: Int, page: Int) {
        addDisposable(
            getChatLogUseCase.create(GetChatLogUseCase.Params(roomId, page))
                .map(chatModelMapper::mapFrom)
                .doOnSubscribe { showLoadingItemEvent.call() }
                .doOnTerminate { hideLoadingItemEvent.call() }
                .subscribe({
                    newItems.value = it
                    Log.d("DEBUGLOG", page.toString())
                }, {
                })
        )
    }
}