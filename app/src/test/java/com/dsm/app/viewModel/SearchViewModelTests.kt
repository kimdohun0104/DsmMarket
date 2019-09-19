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
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var addSearchHistoryUseCase: AddSearchHistoryUseCase

    @Mock
    private lateinit var getSearchHistoryUseCase: GetSearchHistoryUseCase

    @Mock
    private lateinit var deleteSearchHistoryUseCase: DeleteSearchHistoryUseCase

    private val searchHistoryModelMapper = SearchHistoryModelMapper()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = SearchViewModel(addSearchHistoryUseCase, getSearchHistoryUseCase, deleteSearchHistoryUseCase, searchHistoryModelMapper)
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