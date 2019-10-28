package com.dsm.dsmmarketandroid.presentation.model

sealed class ChatModel {
    data class MyChat(val content: String, val time: String) : ChatModel()

    data class ForeignChat(val content: String, val time: String) : ChatModel()

    data class Date(val date: String) : ChatModel()
}