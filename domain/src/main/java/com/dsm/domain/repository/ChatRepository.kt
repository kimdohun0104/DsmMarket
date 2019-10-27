package com.dsm.domain.repository

import com.dsm.domain.entity.ChatRoom
import io.reactivex.Flowable

interface ChatRepository {

    fun createRoom(postId: Int, type: Int): Flowable<Int>

    fun getChatRoom(): Flowable<List<ChatRoom>>
}