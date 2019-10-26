package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Product
import com.dsm.domain.usecase.GetInterestUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestViewModel
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

class InterestViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getInterestUseCase: GetInterestUseCase

    private val productModelMapper = ProductModelMapper()

    private lateinit var viewModel: InterestViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = InterestViewModel(getInterestUseCase, productModelMapper)
    }

    @Test
    fun `isPurchaseEmpty false test`() {
        viewModel.purchaseList.value = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))

        assertFalse(viewModel.isPurchaseEmpty.test().value())
    }

    @Test
    fun `isPurchaseEmpty true test`() {
        viewModel.purchaseList.value = listOf()

        assertTrue(viewModel.isPurchaseEmpty.test().value())
    }

    @Test
    fun `isRentEmpty false test`() {
        viewModel.rentList.value = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))

        assertFalse(viewModel.isRentEmpty.test().value())
    }

    @Test
    fun `isRentEmpty true test`() {
        viewModel.rentList.value = listOf()

        assertTrue(viewModel.isRentEmpty.test().value())
    }

    @Test
    fun `get interest purchase success test`() {
        val response = listOf(Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        `when`(getInterestUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.getInterestPurchase()

        viewModel.purchaseList.test().assertValue(productModelMapper.mapFrom(response))
    }

    @Test
    fun getInterestRent() {
        val response = listOf(
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"),
            Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE")
        )
        `when`(getInterestUseCase.create(1))
            .thenReturn(Flowable.just(response))

        viewModel.getInterestRent()

        viewModel.rentList.test().assertValue(productModelMapper.mapFrom(response))
    }
}