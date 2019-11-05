package com.dsm.data.mapper

import com.dsm.data.remote.entity.ChatLogListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatLog

class ChatLogMapper : Mapper<ChatLogListEntity, List<ChatLog>> {
    override fun mapFrom(from: ChatLogListEntity): List<ChatLog> =
        from.chatLogList.map {
            ChatLog(
                me = it.me,
                message = it.message
            )
        }

}