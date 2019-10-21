package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class CreateRoomUseCase(private val chatRepository: ChatRepository) : UseCase<CreateRoomUseCase.Params, String>() {
    override fun create(data: Params): Flowable<String> =
        chatRepository.createRoom(data.postId, data.type)

    data class Params(val postId: Int, val type: Int)
}