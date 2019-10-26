package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Recommend
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail.PurchaseDetailViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PurchaseDetailViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPurchaseDetailUseCase: GetPurchaseDetailUseCase

    @Mock
    private lateinit var interestUseCase: InterestUseCase

    @Mock
    private lateinit var unInterestUseCase: UnInterestUseCase

    @Mock
    private lateinit var getRecommendUseCase: GetRecommendUseCase

    @Mock
    private lateinit var getRelatedUseCase: GetRelatedUseCase

    @Mock
    private lateinit var createRoomUseCase: CreateRoomUseCase

    private val purchaseDetailModelMapper = PurchaseDetailModelMapper()
    private val recommendModelMapper = RecommendModelMapper()

    private lateinit var viewModel: PurchaseDetailViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = PurchaseDetailViewModel(getPurchaseDetailUseCase, interestUseCase, unInterestUseCase, getRecommendUseCase, getRelatedUseCase, createRoomUseCase, purchaseDetailModelMapper, recommendModelMapper)
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

    @Test
    fun getRecommendProductSuccess() {
        val response = listOf(
            Recommend(0, "TITLE", "IMG")
        )
        `when`(getRecommendUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.getRecommendProduct(0)

        viewModel.recommendList.test().assertValue(recommendModelMapper.mapFrom(response))
    }

    @Test
    fun getRelatedProductSuccess() {
        val response = listOf(
            Recommend(0, "TITLE", "IMG")
        )
        `when`(getRelatedUseCase.create(GetRelatedUseCase.Params(0, 0)))
            .thenReturn(Flowable.just(response))

        viewModel.getRelatedProduct(0)

        viewModel.relatedList.test().assertValue(recommendModelMapper.mapFrom(response))
    }
}