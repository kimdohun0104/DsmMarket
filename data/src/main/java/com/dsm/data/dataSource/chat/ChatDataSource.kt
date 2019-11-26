package com.dsm.data.dataSource.chat

import com.dsm.data.remote.entity.ChatLogListEntity
import com.dsm.data.remote.entity.ChatRoomListEntity
import io.reactivex.Flowable
import retrofit2.Response

interface ChatDataSource {

    fun createRoom(postId: Int, type: Int): Flowable<Map<String, Any>>

    fun getChatRoom(): Flowable<ChatRoomListEntity>

    fun joinRoom(roomId: Int): Flowable<Response<HashMap<String, String>>>

    fun getChatLog(roomId: Int, count: Int): Flowable<ChatLogListEntity>
}