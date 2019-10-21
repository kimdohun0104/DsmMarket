package com.dsm.dsmmarketandroid.di

import com.dsm.data.paging.purchase.PurchaseDataFactory
import com.dsm.data.paging.rent.RentDataFactory
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickViewModel
import com.dsm.dsmmarketandroid.presentation.ui.chatList.ChatListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestViewModel
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginViewModel
import com.dsm.dsmmarketandroid.presentation.ui.modify.purchase.ModifyPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.ModifyRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword.ForgotPasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseImage.PurchaseImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseList.PurchaseListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.rentImage.RentImageViewModel
import com.dsm.dsmmarketandroid.presentation.ui.rentList.RentListViewModel
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchViewModel
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpViewModel
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { ChangePasswordViewModel(get()) }

    viewModel { ChangeNickViewModel(get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { PostCategoryViewModel(get(), get()) }

    viewModel { PostPurchaseViewModel(get()) }

    viewModel { PostRentViewModel(get()) }

    viewModel { PasswordConfirmViewModel(get()) }

    viewModel { PurchaseDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { RentDetailViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { CommentViewModel(get(), get()) }

    viewModel { AddCommentViewModel(get()) }

    viewModel { RecentViewModel(get(), get(), get()) }

    viewModel { SearchViewModel(get(), get()) }

    viewModel { InterestViewModel(get(), get()) }

    viewModel { MyPostViewModel(get(), get(), get(), get(), get()) }

    viewModel { ModifyPurchaseViewModel(get(), get(), get()) }

    viewModel { ModifyRentViewModel(get(), get(), get()) }

    viewModel { RentImageViewModel(get()) }

    viewModel { PurchaseImageViewModel(get()) }

    viewModel { ForgotPasswordViewModel(get()) }

    viewModel { (purchaseDataFactory: PurchaseDataFactory) -> PurchaseListViewModel(purchaseDataFactory, get()) }

    viewModel { (rentDataFactory: RentDataFactory) -> RentListViewModel(rentDataFactory, get()) }

    viewModel { ChatListViewModel(get(), get()) }
}