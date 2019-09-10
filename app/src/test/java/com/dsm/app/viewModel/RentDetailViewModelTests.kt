package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.rentDetail.RentDetailViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
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
    private lateinit var rentDetailModelMapper: RentDetailModelMapper
    private lateinit var viewModel: RentDetailViewModel

    @Before
    fun init() {
        getRentDetailUseCase = mock(GetRentDetailUseCase::class.java)
        rentDetailModelMapper = RentDetailModelMapper()
        viewModel = RentDetailViewModel(getRentDetailUseCase, rentDetailModelMapper)
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
            img = "IMG"
        )
        `when`(getRentDetailUseCase.create(0)).thenReturn(Flowable.just(response))

        viewModel.getRentDetail(0)

        val result = rentDetailModelMapper.mapFrom(response)

        viewModel.rentDetail.test().assertValue(result)
    }
}