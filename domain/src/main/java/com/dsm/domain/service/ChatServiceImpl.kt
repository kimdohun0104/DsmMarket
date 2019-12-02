package com.dsm.domain.service

import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import com.dsm.domain.repository.ChatRepository
import com.dsm.domain.toResource
import io.reactivex.Flowable

class ChatServiceImpl(
    private val repository: ChatRepository,
    private val errorHandler: ErrorHandler
) : ChatService {

    override fun createRoom(postId: Int, type: Int): Flowable<Int> =
        repository.createRoom(postId, type).map {
            (it["roomId"] as Double).toInt()
        }

    override fun getChatRoom(): Flowable<Resource<List<ChatRoom>>> =
        repository.getChatRoom().toResource(errorHandler)

    override fun joinRoom(roomId: Int): Flowable<Resource<String>> =
        repository.joinRoom(roomId)
            .map { it["email"] ?: "" }
            .toResource(errorHandler)

    override fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>> =
        repository.getChatLog(roomId, count)

}