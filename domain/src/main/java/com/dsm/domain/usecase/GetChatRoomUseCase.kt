package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.error.Resource
import com.dsm.domain.service.ChatService
import io.reactivex.Flowable

class GetChatRoomUseCase(private val chatService: ChatService) : UseCase<Unit, Resource<List<ChatRoom>>>() {
    override fun create(data: Unit): Flowable<Resource<List<ChatRoom>>> =
        chatService.getChatRoom()

}