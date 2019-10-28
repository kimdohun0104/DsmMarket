package com.dsm.data.mapper

import com.dsm.data.remote.entity.ChatRoomListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatRoom

class ChatRoomMapper : Mapper<ChatRoomListEntity, List<ChatRoom>> {
    override fun mapFrom(from: ChatRoomListEntity): List<ChatRoom> =
        from.list.map {
            ChatRoom(
                title = it.title,
                picture = it.picture,
                roomId = it.roomId
            )
        }

}