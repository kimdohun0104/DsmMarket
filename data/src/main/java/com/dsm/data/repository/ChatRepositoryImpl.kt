package com.dsm.data.repository

import com.dsm.data.dataSource.chat.ChatDataSource
import com.dsm.data.mapper.ChatRoomMapper
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable

class ChatRepositoryImpl(private val chatDataSource: ChatDataSource) : ChatRepository {

    private val chatRoomMapper = ChatRoomMapper()

    override fun createRoom(postId: Int, type: Int): Flowable<Int> =
        chatDataSource.createRoom(postId, type).map { it["roomId"] }

    override fun getChatRoom(): Flowable<List<ChatRoom>> =
        chatDataSource.getChatRoom().map(chatRoomMapper::mapFrom)
}