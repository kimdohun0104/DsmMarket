package com.dsm.data.mapper

import com.dsm.data.remote.entity.ChatRoomListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatRoom

class ChatRoomMapper : Mapper<ChatRoomListEntity, List<ChatRoom>> {
    override fun mapFrom(from: ChatRoomListEntity): List<ChatRoom> =
        from.list.map {
            ChatRoom(
                roomName = it.roomName,
                recentChat = it.recentChat,
                picture = it.picture,
                nameSpace = it.nameSpace
            )
        }

}