package com.dsm.dsmmarketandroid.presentation.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dsm.data.local.db.dao.SearchDao
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel
import com.dsm.dsmmarketandroid.presentation.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class SearchViewModel(private val searchDao: SearchDao) : BaseViewModel() {

    val searchText = MutableLiveData<String>()

    val searchHistoryList = MutableLiveData<List<String>>()
    val intentSearchResult = SingleLiveEvent<String>()

    val isSearchEnable: LiveData<Boolean> = Transformations.map(searchText) { it != "" }

    val finishActivityEvent = SingleLiveEvent<Any>()

    fun search() {
        intentSearchResult.value = searchText.value!!
        finishActivityEvent.call()
        addDisposable(searchDao.addSearchHistory(SearchHistoryRoomEntity(searchText.value ?: "")).subscribe())
    }

    fun getSearchHistory() {
        addDisposable(
            searchDao.getSearchHistory()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { searchHistoryList.value = it.map { it.content } }
        )
    }

    fun onClickSearchHistory(content: String) {
        intentSearchResult.value = content
        finishActivityEvent.call()
        addDisposable(searchDao.addSearchHistory(SearchHistoryRoomEntity(content)).subscribe())
    }

    fun deleteSearchHistory(content: String) =
        addDisposable(searchDao.deleteSearchHistory(content).subscribe())
}