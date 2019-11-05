package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.ChatLog
import com.dsm.dsmmarketandroid.presentation.model.ChatModel

class ChatModelMapper : Mapper<List<ChatLog>, List<ChatModel>> {
    override fun mapFrom(from: List<ChatLog>): List<ChatModel> {
        val result = arrayListOf<ChatModel>()
        var lastDate = from[0].date
        from.forEach {
            if (it.me) {
                result.add(ChatModel.MyChat(it.message, it.time))
            } else {
                result.add(ChatModel.ForeignChat(it.message, it.time))
            }
            if (lastDate != it.date) {
                result.add(ChatModel.Date(lastDate))
                lastDate = it.date
            }
        }
        result.add(ChatModel.Date(lastDate))
        return result
    }
}