package com.dsm.data.repository

import com.dsm.data.dataSource.chat.ChatDataSource
import com.dsm.data.mapper.ChatLogMapper
import com.dsm.data.mapper.ChatRoomMapper
import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class ChatRepositoryImpl(
    private val chatDataSource: ChatDataSource,
    private val chatRoomMapper: ChatRoomMapper,
    private val chatLogMapper: ChatLogMapper
) : ChatRepository {

    override fun createRoom(postId: Int, type: Int): Flowable<Map<String, Any>> =
        chatDataSource.createRoom(postId, type)

    override fun getChatRoom(): Flowable<List<ChatRoom>> =
        chatDataSource.getChatRoom().map(chatRoomMapper::mapFrom)

    override fun joinRoom(roomId: Int): Flowable<Map<String, String>> =
        chatDataSource.joinRoom(roomId)

    override fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>> =
        chatDataSource.getChatLog(roomId, count).map(chatLogMapper::mapFrom)
}