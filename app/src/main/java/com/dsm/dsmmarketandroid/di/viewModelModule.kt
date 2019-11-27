package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatViewModel
import com.dsm.dsmmarketandroid.presentation.ui.chat.chatList.ChatListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.comment.addComment.AddCommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseImage.PurchaseImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseList.PurchaseListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentDetail.RentDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage.RentImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList.RentListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.changeNick.ChangeNickViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.interest.InterestViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.recent.RecentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.modify.purchase.ModifyPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.ModifyRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword.ForgotPasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ChangePasswordViewModel(get()) }

    viewModel { ChangeNickViewModel(get()) }

    viewModel { PostCategoryViewModel(get(), get()) }

    viewModel { PostPurchaseViewModel(get()) }

    viewModel { PostRentViewModel(get()) }

    viewModel { PasswordConfirmViewModel(get()) }

    viewModel { PurchaseDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { RentDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { CommentViewModel(get(), get()) }

    viewModel { AddCommentViewModel(get()) }

    viewModel { RecentViewModel(get(), get(), get()) }

    viewModel { SearchViewModel(get(), get(), get()) }

    viewModel { InterestViewModel(get(), get()) }

    viewModel { MyPostViewModel(get(), get(), get(), get(), get()) }

    viewModel { ModifyPurchaseViewModel(get(), get(), get()) }

    viewModel { ModifyRentViewModel(get(), get(), get()) }

    viewModel { RentImageViewModel(get()) }

    viewModel { PurchaseImageViewModel(get()) }

    viewModel { ForgotPasswordViewModel(get()) }

    viewModel { (purchaseDataFactory: PurchaseDataFactory) -> PurchaseListViewModel(purchaseDataFactory, get()) }

    viewModel { (rentDataFactory: RentDataFactory) -> RentListViewModel(rentDataFactory, get()) }

    viewModel { ChatListViewModel(get(), get(), get()) }

    viewModel { ChatViewModel(get(), get()) }
}