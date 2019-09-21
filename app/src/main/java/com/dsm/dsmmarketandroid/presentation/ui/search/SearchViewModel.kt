package com.dsm.dsmmarketandroid.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.DeleteSearchHistoryUseCase
import com.dsm.domain.usecase.GetSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class SearchViewModel(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase
) : BaseViewModel() {

    val searchText = MutableLiveData<String>()

    val searchHistoryList = MutableLiveData<List<String>>()
    val intentSearchResult = MutableLiveData<String>()

    val isSearchEnable = Transformations.map(searchText) { it != "" }

    fun search() {
        intentSearchResult.value = searchText.value!!
    }

    fun getSearchHistory() {
        addDisposable(
            getSearchHistoryUseCase.create(Unit)
                .subscribe({
                    searchHistoryList.value = it
                }, {
                })
        )
    }

    fun onClickSearchHistory(content: String) {
        intentSearchResult.value = content
    }

    fun deleteSearchHistory(content: String) =
        addDisposable(deleteSearchHistoryUseCase.create(content).subscribe())
}