package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.ChatService
import io.reactivex.Flowable

class JoinRoomUseCase(private val chatService: ChatService) : UseCase<Int, Resource<String>>() {
    override fun create(data: Int): Flowable<Resource<String>> =
        chatService.joinRoom(data)

}