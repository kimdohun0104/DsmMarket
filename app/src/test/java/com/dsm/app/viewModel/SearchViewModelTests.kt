package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.SearchHistory
import com.dsm.domain.usecase.AddSearchHistoryUseCase
import com.dsm.domain.usecase.DeleteSearchHistoryUseCase
import com.dsm.domain.usecase.GetSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.SearchHistoryModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchViewModel
import com.jraska.livedata.test
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class SearchViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var addSearchHistoryUseCase: AddSearchHistoryUseCase
    private lateinit var getSearchHistoryUseCase: GetSearchHistoryUseCase
    private lateinit var deleteSearchHistoryUsecase: DeleteSearchHistoryUseCase
    private lateinit var searchHistoryModelMapper: SearchHistoryModelMapper
    private lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        addSearchHistoryUseCase = mock(AddSearchHistoryUseCase::class.java)
        getSearchHistoryUseCase = mock(GetSearchHistoryUseCase::class.java)
        deleteSearchHistoryUsecase = mock(DeleteSearchHistoryUseCase::class.java)
        searchHistoryModelMapper = SearchHistoryModelMapper()
        viewModel = SearchViewModel(addSearchHistoryUseCase, getSearchHistoryUseCase, deleteSearchHistoryUsecase, searchHistoryModelMapper)
    }

    @Test
    fun searchSuccess() {
        val searchText = "SEARCH_TEXT"
        viewModel.searchText.value = searchText

        `when`(addSearchHistoryUseCase.create(SearchHistory(searchText)))
            .thenReturn(Completable.complete())

        viewModel.search()

        viewModel.intentSearchResult.test().assertValue(searchText)
    }

    @Test
    fun getSearchHistorySuccess() {
        val response = listOf(SearchHistory("CONTENT"))
        `when`(getSearchHistoryUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getSearchHistory()

        val afterMap = searchHistoryModelMapper.mapFrom(response)
        viewModel.searchHistoryList.test().assertValue(afterMap)
    }
}