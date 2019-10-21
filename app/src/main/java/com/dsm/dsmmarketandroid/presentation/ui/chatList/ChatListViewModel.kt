package com.dsm.dsmmarketandroid.presentation.ui.chatList

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetChatRoomUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ChatRoomModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ChatRoomModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ChatListViewModel(
    private val getChatRoomUseCase: GetChatRoomUseCase,
    private val chatRoomModelMapper: ChatRoomModelMapper
) : BaseViewModel() {

    val toastServerErrorEvent = SingleLiveEvent<Any>()

    val chatRoomList = MutableLiveData<List<ChatRoomModel>>()

    fun getChatRoom() {
        addDisposable(
            getChatRoomUseCase.create(Unit)
                .map(chatRoomModelMapper::mapFrom)
                .subscribe({
                    chatRoomList.value = it
                }, {
                    toastServerErrorEvent.call()
                })
        )
    }
}