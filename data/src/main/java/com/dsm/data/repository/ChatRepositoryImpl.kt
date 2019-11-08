package com.dsm.data.repository

import com.dsm.data.dataSource.chat.ChatDataSource
import com.dsm.data.mapper.ChatLogMapper
import com.dsm.data.mapper.ChatRoomMapper
import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.repository.ChatRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class ChatRepositoryImpl(private val chatDataSource: ChatDataSource) : ChatRepository {

    private val chatRoomMapper = ChatRoomMapper()
    private val chatLogMapper = ChatLogMapper()

    override fun createRoom(postId: Int, type: Int): Flowable<Int> =
        chatDataSource.createRoom(postId, type).map { it["roomId"] }

    override fun getChatRoom(): Flowable<List<ChatRoom>> =
        chatDataSource.getChatRoom().map(chatRoomMapper::mapFrom)

    override fun joinRoom(roomId: Int): Flowable<String> =
        chatDataSource.joinRoom(roomId).map {
            if (it.code() != 200) throw HttpException(it)
            it.body()?.get("email") ?: ""
        }

    override fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>> =
        chatDataSource.getChatLog(roomId, count).map(chatLogMapper::mapFrom)
}