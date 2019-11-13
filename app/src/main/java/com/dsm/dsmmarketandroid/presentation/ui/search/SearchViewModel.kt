package com.dsm.dsmmarketandroid.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.domain.usecase.AddSearchHistoryUseCase
import com.dsm.domain.usecase.DeleteSearchHistoryUseCase
import com.dsm.domain.usecase.GetSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent

class SearchViewModel(
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val addSearchHistoryUseCase: AddSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase
) : BaseViewModel() {

    val searchText = MutableLiveData<String>()

    val searchHistoryList = MutableLiveData<List<String>>()
    val intentSearchResult = SingleLiveEvent<String>()

    val isSearchEnable: LiveData<Boolean> = Transformations.map(searchText) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()

    fun search() {
        intentSearchResult.value = searchText.value!!
        finishActivityEvent.call()
        addDisposable(addSearchHistoryUseCase.create(searchText.value!!).subscribe())
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
        finishActivityEvent.call()
        addDisposable(addSearchHistoryUseCase.create(content).subscribe())
    }

    fun deleteSearchHistory(content: String) =
        addDisposable(deleteSearchHistoryUseCase.create(content).subscribe())
}