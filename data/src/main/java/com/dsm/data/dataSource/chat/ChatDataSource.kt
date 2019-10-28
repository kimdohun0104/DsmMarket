package com.dsm.data.dataSource.chat

import com.dsm.data.remote.entity.ChatRoomEntity
import com.dsm.data.remote.entity.ChatRoomListEntity
import io.reactivex.Flowable
import retrofit2.Response

interface ChatDataSource {

    fun createRoom(postId: Int, type: Int): Flowable<Map<String, Int>>

    fun getChatRoom(): Flowable<ChatRoomListEntity>

    fun joinRoom(roomId: Int): Flowable<Response<HashMap<String, String>>>
}