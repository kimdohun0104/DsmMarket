package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.ChatLog
import com.dsm.domain.repository.ChatRepository
import com.dsm.domain.service.ChatService
import io.reactivex.Flowable

class GetChatLogUseCase(private val chatRepository: ChatService) : UseCase<GetChatLogUseCase.Params, List<ChatLog>>() {
    override fun create(data: Params): Flowable<List<ChatLog>> =
        chatRepository.getChatLog(data.roomId, data.count)

    data class Params(val roomId: Int, val count: Int)
}