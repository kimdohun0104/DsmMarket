package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.CommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.addComment.AddCommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.recent.RecentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postRent.PostRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.modifyPurchase.ModifyPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseImage.PurchaseImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseList.PurchaseListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent.ModifyRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage.RentImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList.RentListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { PostCategoryViewModel(get(), get()) }

    viewModel { PostPurchaseViewModel(get()) }

    viewModel { PostRentViewModel(get()) }

    viewModel { CommentViewModel(get(), get()) }

    viewModel { AddCommentViewModel(get()) }

    viewModel { RecentViewModel(get(), get(), get()) }

    viewModel { SearchViewModel(get(), get(), get()) }

    viewModel { MyPostViewModel(get(), get(), get(), get(), get()) }

    viewModel { ModifyPurchaseViewModel(get(), get(), get()) }

    viewModel { ModifyRentViewModel(get(), get(), get()) }

    viewModel { RentImageViewModel(get()) }

    viewModel { PurchaseImageViewModel(get()) }

    viewModel { (purchaseDataFactory: PurchaseDataFactory) -> PurchaseListViewModel(purchaseDataFactory, get()) }

    viewModel { (rentDataFactory: RentDataFactory) -> RentListViewModel(rentDataFactory, get()) }
}