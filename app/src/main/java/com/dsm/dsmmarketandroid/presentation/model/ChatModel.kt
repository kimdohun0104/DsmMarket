package com.dsm.dsmmarketandroid.presentation.model

sealed class ChatModel {
    data class MyChat(val content: String, val time: String, val date: String) : ChatModel()

    data class ForeignChat(val content: String, val time: String, val date: String) : ChatModel()

    data class Date(val date: String) : ChatModel()

    object Loading : ChatModel()
}