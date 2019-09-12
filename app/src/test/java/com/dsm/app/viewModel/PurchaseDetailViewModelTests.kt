package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.InterestUseCase
import com.dsm.domain.usecase.UnInterestUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
    private lateinit var interestUseCase: InterestUseCase
    private lateinit var unInterestUseCase: UnInterestUseCase
    private lateinit var purchaseDetailModelMapper: PurchaseDetailModelMapper
    private lateinit var viewModel: PurchaseDetailViewModel

    @Before
    fun init() {
        getPurchaseDetailUseCase = mock(GetPurchaseDetailUseCase::class.java)
        interestUseCase = mock(InterestUseCase::class.java)
        unInterestUseCase = mock(UnInterestUseCase::class.java)
        purchaseDetailModelMapper = PurchaseDetailModelMapper()
        viewModel = PurchaseDetailViewModel(getPurchaseDetailUseCase, interestUseCase, unInterestUseCase, purchaseDetailModelMapper)
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
            img = listOf("IMAGE1", "asdf"),
            isInterest = true
        )
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.getPurchaseDetail(0)

        val result = purchaseDetailModelMapper.mapFrom(response)

        viewModel.purchaseDetail.test().assertValue(result)
    }

    @Test
    fun `interest purchase success`() {
        viewModel.isInterest.value = false

        `when`(interestUseCase.create(InterestUseCase.Params(0, 0)))
            .thenReturn(Flowable.just(Unit))

        viewModel.onClickInterest(0)

        assertTrue(viewModel.isInterest.test().value())
        viewModel.toastInterestEvent.test().assertHasValue()
    }

    @Test
    fun `un interest purchase success`() {
        viewModel.isInterest.value = true

        `when`(unInterestUseCase.create(UnInterestUseCase.Params(0, 0)))
            .thenReturn(Flowable.just(Unit))

        viewModel.onClickInterest(0)

        assertFalse(viewModel.isInterest.test().value())
        viewModel.toastUnInterestEvent.test().assertHasValue()
    }
}