package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class JoinRoomUseCase(private val chatRepository: ChatRepository) : UseCase<Int, String>() {
    override fun create(data: Int): Flowable<String> =
        chatRepository.joinRoom(data)

}