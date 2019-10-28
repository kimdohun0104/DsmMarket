package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatRoom
import com.dsm.dsmmarketandroid.presentation.model.ChatRoomModel

class ChatRoomModelMapper : Mapper<List<ChatRoom>, List<ChatRoomModel>> {
    override fun mapFrom(from: List<ChatRoom>): List<ChatRoomModel> =
        from.map {
            ChatRoomModel(
                title = it.title,
                roomId = it.roomId,
                picture = it.picture
            )
        }

}