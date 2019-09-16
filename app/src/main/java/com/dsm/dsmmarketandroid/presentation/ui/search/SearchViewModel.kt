package com.dsm.dsmmarketandroid.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.usecase.AddSearchHistoryUseCase
import com.dsm.domain.usecase.DeleteSearchHistoryUseCase
import com.dsm.domain.usecase.GetSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.mapper.SearchHistoryModelMapper
import com.dsm.dsmmarketandroid.presentation.model.SearchHistoryModel

class SearchViewModel(
    private val addSearchHistoryUseCase: AddSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase,
    private val searchHistoryModelMapper: SearchHistoryModelMapper
) : BaseViewModel() {

    val searchText = MutableLiveData<String>()

    val searchHistoryList = MutableLiveData<List<SearchHistoryModel>>()

    val intentSearchResult = MutableLiveData<String>()

    fun search() {
        val searchText = searchText.value!!
        addDisposable(addSearchHistoryUseCase.create(SearchHistory(searchText)).subscribe())
        intentSearchResult.value = searchText
    }

    fun getSearchHistory() {
        addDisposable(
            getSearchHistoryUseCase.create(Unit)
                .subscribe({
                    searchHistoryList.value = searchHistoryModelMapper.mapFrom(it)
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