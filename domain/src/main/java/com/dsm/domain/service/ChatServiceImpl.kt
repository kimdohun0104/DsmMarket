package com.dsm.domain.service

import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class ChatServiceImpl(
    private val repository: ChatRepository,
    private val errorHandler: ErrorHandler
) : ChatService {

    override fun createRoom(postId: Int, type: Int): Flowable<Int> =
        repository.createRoom(postId, type)
            .map { (it["roomId"] as Double).toInt() }
            .handleError(errorHandler)

    override fun getChatRoom(): Flowable<List<ChatRoom>> =
        repository.getChatRoom().handleError(errorHandler)

    override fun joinRoom(roomId: Int): Flowable<String> =
        repository.joinRoom(roomId)
            .map { it["email"] ?: "" }
            .handleError(errorHandler)

    override fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>> =
        repository.getChatLog(roomId, count).handleError(errorHandler)

}