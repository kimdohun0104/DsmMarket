package com.dsm.domain.service

import com.dsm.domain.entity.ChatLog
import com.dsm.domain.entity.ChatRoom
import com.dsm.domain.error.Success
import io.reactivex.Flowable

interface ChatService {
    fun createRoom(postId: Int, type: Int): Flowable<Int>

    fun getChatRoom(): Flowable<List<ChatRoom>>

    fun joinRoom(roomId: Int): Flowable<String>

    fun getChatLog(roomId: Int, count: Int): Flowable<List<ChatLog>>
}