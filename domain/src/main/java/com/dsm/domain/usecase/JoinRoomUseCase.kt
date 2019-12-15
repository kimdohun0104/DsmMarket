package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.ChatService
import io.reactivex.Flowable

class JoinRoomUseCase(private val chatService: ChatService) : UseCase<Int, String>() {
    override fun create(data: Int): Flowable<String> =
        chatService.joinRoom(data)

}