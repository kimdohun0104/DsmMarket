package com.dsm.dsmmarketandroid.di.comment

import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.data.dataSource.comment.CommentDataSourceImpl
import com.dsm.data.mapper.CommentMapper
import com.dsm.data.repository.CommentRepositoryImpl
import com.dsm.domain.repository.CommentRepository
import com.dsm.domain.service.CommentService
import com.dsm.domain.service.CommentServiceImpl
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.domain.usecase.PostCommentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.CommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.addComment.AddCommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commentModule = module {

    factory<CommentDataSource> { CommentDataSourceImpl(get(), get()) }

    factory { CommentMapper() }

    factory<CommentRepository> { CommentRepositoryImpl(get(), get()) }

    factory<CommentService> { CommentServiceImpl(get(), get()) }

    factory { CommentModelMapper() }

    // comment list
    factory { GetCommentUseCase(get()) }

    viewModel { CommentViewModel(get(), get()) }

    // add comment
    factory { PostCommentUseCase(get()) }

    viewModel { AddCommentViewModel(get()) }
}