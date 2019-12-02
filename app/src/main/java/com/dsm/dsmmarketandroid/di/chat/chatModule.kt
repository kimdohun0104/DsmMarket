package com.dsm.dsmmarketandroid.di.chat

import com.dsm.data.dataSource.chat.ChatDataSource
import com.dsm.data.dataSource.chat.ChatDataSourceImpl
import com.dsm.data.mapper.ChatLogMapper
import com.dsm.data.mapper.ChatRoomMapper
import com.dsm.data.repository.ChatRepositoryImpl
import com.dsm.domain.repository.ChatRepository
import com.dsm.domain.service.ChatService
import com.dsm.domain.service.ChatServiceImpl
import com.dsm.domain.usecase.CreateRoomUseCase
import com.dsm.domain.usecase.GetChatLogUseCase
import com.dsm.domain.usecase.GetChatRoomUseCase
import com.dsm.domain.usecase.JoinRoomUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.ChatModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.ChatRoomModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.chat.ChatViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.chat.chatList.ChatListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {

    factory { ChatRoomMapper() }

    factory { ChatLogMapper() }

    factory<ChatDataSource> { ChatDataSourceImpl(get()) }

    factory<ChatRepository> { ChatRepositoryImpl(get(), get(), get()) }

    factory<ChatService> { ChatServiceImpl(get(), get()) }

    factory { CreateRoomUseCase(get()) }

    factory { GetChatRoomUseCase(get()) }

    factory { JoinRoomUseCase(get()) }

    factory { GetChatLogUseCase(get()) }

    // chat
    factory { ChatModelMapper() }

    viewModel { ChatViewModel(get(), get()) }

    // chat room list
    factory { ChatRoomModelMapper() }

    viewModel { ChatListViewModel(get(), get(), get()) }
}