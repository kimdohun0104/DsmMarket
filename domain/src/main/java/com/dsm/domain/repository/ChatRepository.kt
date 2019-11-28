package com.dsm.domain.repository

import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import io.reactivex.Flowable

interface ChatRepository {

    fun createRoom(postId: Int, type: Int): Flowable<Map<String, Any>>

    fun getChatRoom(): Flowable<List<ChatRoom>>

    fun joinRoom(roomId: Int): Flowable<Map<String, String>>

    fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>>
}