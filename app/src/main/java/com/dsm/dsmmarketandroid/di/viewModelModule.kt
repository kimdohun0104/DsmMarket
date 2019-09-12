package com.dsm.dsmmarketandroid.di

import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickViewModel
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginViewModel
import com.dsm.dsmmarketandroid.presentation.ui.me.MeViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordCodeConfirm.PasswordCodeConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmViewModel
import com.dsm.dsmmarketandroid.presentation.ui.password.sendPasswordCode.SendPasswordCodeViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postPurchase.PostPurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryViewModel
import com.dsm.dsmmarketandroid.presentation.ui.purchase.PurchaseViewModel
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.rent.RentViewModel
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailViewModel
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpViewModel
import com.dsm.dsmmarketandroid.presentation.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }

    viewModel { SignUpViewModel(get()) }

    viewModel { SendPasswordCodeViewModel(get()) }

    viewModel { PasswordCodeConfirmViewModel(get()) }

    viewModel { ChangePasswordViewModel(get()) }

    viewModel { MeViewModel(get()) }

    viewModel { ChangeNickViewModel(get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { PostCategoryViewModel(get(), get()) }

    viewModel { PostPurchaseViewModel(get()) }

    viewModel { PostRentViewModel(get()) }

    viewModel { PurchaseViewModel(get(), get()) }

    viewModel { RentViewModel(get(), get()) }

    viewModel { PasswordConfirmViewModel(get()) }

    viewModel { PurchaseDetailViewModel(get(), get(), get(), get()) }

    viewModel { RentDetailViewModel(get(), get(), get(), get()) }

    viewModel { CommentViewModel(get(), get()) }

    viewModel { AddCommentViewModel(get()) }
}