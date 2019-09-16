package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Recent
import com.dsm.domain.usecase.GetRecentPurchaseUseCase
import com.dsm.domain.usecase.GetRecentRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.RecentMapper
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RecentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getRecentPurchaseUseCase: GetRecentPurchaseUseCase
    private lateinit var getRecentRentUseCase: GetRecentRentUseCase
    private lateinit var recentModelMapper: RecentMapper
    private lateinit var viewModel: RecentViewModel

    @Before
    fun init() {
        getRecentPurchaseUseCase = mock(GetRecentPurchaseUseCase::class.java)
        getRecentRentUseCase = mock(GetRecentRentUseCase::class.java)
        recentModelMapper = RecentMapper()
        viewModel = RecentViewModel(getRecentPurchaseUseCase, getRecentRentUseCase, recentModelMapper)
    }

    @Test
    fun getRecentProductSuccess() {
        val response = listOf(Recent(0, "TITLE", "CREATED_AT", "PRICE", "IMG"))
        `when`(getRecentPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.just(response))
        `when`(getRecentRentUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getRecentProduct()

        val afterMap = recentModelMapper.mapFrom(response)

        viewModel.purchaseList.test().assertValue(afterMap)
        viewModel.rentList.test().assertValue(afterMap)
    }
}