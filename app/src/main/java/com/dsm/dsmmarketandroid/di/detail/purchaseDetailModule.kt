package com.dsm.dsmmarketandroid.di.detail

import com.dsm.data.mapper.PurchaseDetailMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailViewModel
import org.koin.dsl.module

val purchaseDetailModule = module {

    factory { PurchaseDetailMapper() }

    factory { PurchaseDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
}