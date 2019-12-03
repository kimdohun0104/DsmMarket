package com.dsm.dsmmarketandroid.presentation.ui.main.chat.chatList

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetChatRoomUseCase
import com.dsm.domain.usecase.JoinRoomUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.ChatRoomModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ChatRoomModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class ChatListViewModel(
    private val getChatRoomUseCase: GetChatRoomUseCase,
    private val joinRoomUseCase: JoinRoomUseCase,
    private val chatRoomModelMapper: ChatRoomModelMapper
) : BaseViewModel() {

    val toastEvent = SingleLiveEvent<Int>()
    val intentChatActivityEvent = SingleLiveEvent<Bundle>()

    val chatRoomList = MutableLiveData<List<ChatRoomModel>>()

    val showLoadingDialogEvent = SingleLiveEvent<Any>()
    val hideLoadingDialogEvent = SingleLiveEvent<Any>()

    val isRefreshing = MutableLiveData<Boolean>()
    val isProgressVisible = MutableLiveData<Boolean>().apply { value = true }

    val isChatEmpty = Transformations.map(chatRoomList) { it.isEmpty() }

    fun getChatRoom() {
        addDisposable(
            getChatRoomUseCase.create(Unit)
                .doOnTerminate {
                    isRefreshing.value = false
                    isProgressVisible.value = false
                }
                .subscribe({
                    when (it) {
                        is Resource.Success -> chatRoomList.value = chatRoomModelMapper.mapFrom(it.data)
                        is Resource.Error -> {
                            when (it.error) {
                                is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                else -> toastEvent.value = R.string.fail_server_error
                            }
                        }
                    }
                }, {})
        )
    }

    fun joinRoom(roomId: Int, roomTitle: String) {
        addDisposable(
            joinRoomUseCase.create(roomId)
                .doOnSubscribe { showLoadingDialogEvent.call() }
                .doOnTerminate { hideLoadingDialogEvent.call() }
                .subscribe({
                    when (it) {
                        is Resource.Success -> intentChatActivityEvent.value = Bundle().apply {
                            putInt("roomId", roomId)
                            putString("email", it.data)
                            putString("roomTitle", roomTitle)
                        }
                        is Resource.Error -> {
                            when (it.error) {
                                is ErrorEntity.Unauthorized -> toastEvent.value = R.string.fail_unauthorized
                                is ErrorEntity.Gone -> toastEvent.value = R.string.fail_join_chat_room
                                else -> toastEvent.value = R.string.fail_server_error
                            }
                        }
                    }
                }, {})
        )
    }
}