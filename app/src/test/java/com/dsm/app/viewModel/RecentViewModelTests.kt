package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.entity.Product
import com.dsm.domain.usecase.GetRecentPurchaseUseCase
import com.dsm.domain.usecase.GetRecentRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class RecentViewModelTests : BaseTest() {

    @Mock
    private lateinit var getRecentPurchaseUseCase: GetRecentPurchaseUseCase

    @Mock
    private lateinit var getRecentRentUseCase: GetRecentRentUseCase

    private val recentModelMapper = ProductModelMapper()

    private lateinit var viewModel: RecentViewModel

    @Before
    fun init() {
        viewModel = RecentViewModel(getRecentPurchaseUseCase, getRecentRentUseCase, recentModelMapper)
    }

    @Test
    fun getRecentProductSuccess() {
        val response = listOf(Product(0, "TITLE", "CREATED_AT", "PRICE", "IMG"))
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