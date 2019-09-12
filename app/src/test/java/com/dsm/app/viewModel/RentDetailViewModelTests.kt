package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.InterestUseCase
import com.dsm.domain.usecase.UnInterestUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RentDetailViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getRentDetailUseCase: GetRentDetailUseCase
    private lateinit var interestUseCase: InterestUseCase
    private lateinit var unInterestUseCase: UnInterestUseCase
    private lateinit var rentDetailModelMapper: RentDetailModelMapper
    private lateinit var viewModel: RentDetailViewModel

    @Before
    fun init() {
        getRentDetailUseCase = mock(GetRentDetailUseCase::class.java)
        interestUseCase = mock(InterestUseCase::class.java)
        unInterestUseCase = mock(UnInterestUseCase::class.java)
        rentDetailModelMapper = RentDetailModelMapper()
        viewModel = RentDetailViewModel(getRentDetailUseCase, interestUseCase, unInterestUseCase, rentDetailModelMapper)
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
            isInterest = true
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
}