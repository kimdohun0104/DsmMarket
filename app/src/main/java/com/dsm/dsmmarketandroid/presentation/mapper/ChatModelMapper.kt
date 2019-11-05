package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatLog
import com.dsm.dsmmarketandroid.presentation.model.ChatModel

class ChatModelMapper : Mapper<List<ChatLog>, List<ChatModel>> {
    override fun mapFrom(from: List<ChatLog>): List<ChatModel> =
        from.map {
            if (it.me) {
                ChatModel.MyChat(it.message, "")
            } else {
                ChatModel.ForeignChat(it.message, "")
            }
        }

}