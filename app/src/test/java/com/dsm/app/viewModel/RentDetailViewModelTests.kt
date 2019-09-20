package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.usecase.GetRelatedUseCase
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.InterestUseCase
import com.dsm.domain.usecase.UnInterestUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailViewModel
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

class RentDetailViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getRentDetailUseCase: GetRentDetailUseCase

    @Mock
    private lateinit var interestUseCase: InterestUseCase

    @Mock
    private lateinit var getRelatedUseCase: GetRelatedUseCase

    @Mock
    private lateinit var unInterestUseCase: UnInterestUseCase


    private val rentDetailModelMapper = RentDetailModelMapper()
    private val recommendModelMapper = RecommendModelMapper()

    private lateinit var viewModel: RentDetailViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = RentDetailViewModel(getRentDetailUseCase, interestUseCase, unInterestUseCase, getRelatedUseCase, recommendModelMapper, rentDetailModelMapper)
    }

    @Test
    fun getRentDetailSuccess() {
        val response = RentDetail(
            id = 0,
            commentCount = 0,
            possibleTime = "POSSIBLE_TIME",
            price = "PRICE",
            createdAt = "CREATED_AT",
            content = "CONTENT",
            title = "TITLE",
            author = "AUTHOR",
            img = "IMG",
            isInterest = true,
            category = "CATEGORY"
        )
        `when`(getRentDetailUseCase.create(0)).thenReturn(Flowable.just(response))

        viewModel.getRentDetail(0)

        val result = rentDetailModelMapper.mapFrom(response)

        viewModel.rentDetail.test().assertValue(result)
    }

    @Test
    fun interestRentSuccess() {
        viewModel.isInterest.value = false

        `when`(interestUseCase.create(InterestUseCase.Params(0, 1)))
            .thenReturn(Flowable.just(Unit))

        viewModel.onClickInterest(0)

        assertTrue(viewModel.isInterest.test().value())
        viewModel.toastInterestEvent.test().assertHasValue()
    }

    @Test
    fun unInterestRentSuccess() {
        viewModel.isInterest.value = true

        `when`(unInterestUseCase.create(UnInterestUseCase.Params(0, 1)))
            .thenReturn(Flowable.just(Unit))

        viewModel.onClickInterest(0)

        assertFalse(viewModel.isInterest.test().value())
        viewModel.toastUnInterestEvent.test().assertHasValue()
    }

    @Test
    fun getRelatedProductSuccess() {
        val response = listOf(
            Recommend(0, "TITLE", "IMG")
        )

        `when`(getRelatedUseCase.create(GetRelatedUseCase.Params(0, 1)))
            .thenReturn(Flowable.just(response))

        viewModel.getRelatedProduct(0)

        viewModel.relatedList.test().assertValue(recommendModelMapper.mapFrom(response))
    }
}