package com.dsm.dsmmarketandroid.di.search

import com.dsm.dsmmarketandroid.presentation.ui.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    viewModel { SearchViewModel(get()) }
}