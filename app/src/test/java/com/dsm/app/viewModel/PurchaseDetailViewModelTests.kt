package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PurchaseDetailViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getPurchaseDetailUseCase: GetPurchaseDetailUseCase
    private lateinit var purchaseDetailModelMapper: PurchaseDetailModelMapper
    private lateinit var viewModel: PurchaseDetailViewModel

    @Before
    fun init() {
        getPurchaseDetailUseCase = mock(GetPurchaseDetailUseCase::class.java)
        purchaseDetailModelMapper = PurchaseDetailModelMapper()
        viewModel = PurchaseDetailViewModel(getPurchaseDetailUseCase, purchaseDetailModelMapper)
    }

    @Test
    fun getPurchaseDetailSuccess() {
        val response = PurchaseDetail(
            id = 0,
            author = "AUTHOR",
            createdAt = "CREATED_AT",
            price = "PRICE",
            title = "TITLE",
            commentCount = 0,
            content = "CONTENT",
            img = listOf("IMAGE1", "asdf")
        )
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.getPurchaseDetail(0)

        val result = purchaseDetailModelMapper.mapFrom(response)

        viewModel.purchaseDetail.test().assertValue(result)
    }
}