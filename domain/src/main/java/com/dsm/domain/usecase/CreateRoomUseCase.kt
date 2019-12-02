package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.service.ChatService
import io.reactivex.Flowable

class CreateRoomUseCase(private val chatService: ChatService) : UseCase<CreateRoomUseCase.Params, Int>() {
    override fun create(data: Params): Flowable<Int> =
        chatService.createRoom(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}