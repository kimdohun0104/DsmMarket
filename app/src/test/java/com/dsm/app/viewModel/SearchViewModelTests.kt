package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.DeleteSearchHistoryUseCase
import com.dsm.domain.usecase.GetSearchHistoryUseCase
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getSearchHistoryUseCase: GetSearchHistoryUseCase

    @Mock
    private lateinit var deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase


    private lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchViewModel(getSearchHistoryUseCase, deleteSearchHistoryUseCase)
    }

    @Test
    fun searchSuccess() {
        val searchText = "SEARCH_TEXT"
        viewModel.searchText.value = searchText

        viewModel.search()

        viewModel.intentSearchResult.test().assertValue(searchText)
    }

    @Test
    fun getSearchHistorySuccess() {
        val response = listOf("CONTENT")
        `when`(getSearchHistoryUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getSearchHistory()

        viewModel.searchHistoryList.test().assertValue(response)
    }
}