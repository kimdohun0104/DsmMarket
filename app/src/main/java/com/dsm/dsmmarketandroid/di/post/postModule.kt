package com.dsm.dsmmarketandroid.di.post

import com.dsm.data.dataSource.post.PostDataSource
import com.dsm.data.dataSource.post.PostDataSourceImpl
import com.dsm.data.repository.PostRepositoryImpl
import com.dsm.domain.repository.PostRepository
import com.dsm.domain.service.PostService
import com.dsm.domain.service.PostServiceImpl
import com.dsm.domain.usecase.GetPostCategoryUseCase
import com.dsm.domain.usecase.PostPurchaseUseCase
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postRent.PostRentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val postModule = module {

    factory<PostDataSource> { PostDataSourceImpl(get()) }

    factory<PostRepository> { PostRepositoryImpl(get()) }

    factory<PostService> { PostServiceImpl(get(), get()) }

    // post purchase
    factory { PostPurchaseUseCase(get()) }

    viewModel { PostPurchaseViewModel(get()) }

    // post rent
    factory { PostRentUseCase(get()) }

    viewModel { PostRentViewModel(get()) }

    // post category
    factory { PostCategoryModelMapper() }

    factory { GetPostCategoryUseCase(get()) }

    viewModel { PostCategoryViewModel(get(), get()) }
}