package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class GetChatRoomUseCase(private val chatRepository: ChatRepository) : UseCase<Unit, List<ChatRoom>>() {
    override fun create(data: Unit): Flowable<List<ChatRoom>> =
        chatRepository.getChatRoom()

}