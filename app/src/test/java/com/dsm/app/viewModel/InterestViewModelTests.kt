package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.entity.Product
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetInterestPurchaseUseCase
import com.dsm.domain.usecase.GetInterestRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.interest.InterestViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class InterestViewModelTests : BaseTest() {

    @Mock
    private lateinit var getInterestPurchaseUseCase: GetInterestPurchaseUseCase

    @Mock
    private lateinit var getInterestRentUseCase: GetInterestRentUseCase

    private val productModelMapper = ProductModelMapper()

    private lateinit var viewModel: InterestViewModel

    @Before
    fun init() {
        viewModel = InterestViewModel(getInterestRentUseCase, getInterestPurchaseUseCase, productModelMapper)
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
        `when`(getInterestPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.just(Resource.Success(response)))

        viewModel.getPurchaseInterest()

        viewModel.purchaseList.test().assertValue(productModelMapper.mapFrom(response))
    }

    @Test
    fun `get interest purchase failed test`() {
        `when`(getInterestPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

        viewModel.getPurchaseInterest()

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
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
        `when`(getInterestRentUseCase.create(Unit))
            .thenReturn(Flowable.just(Resource.Success(response)))

        viewModel.getRentInterest()

        viewModel.rentList.test().assertValue(productModelMapper.mapFrom(response))
    }

     @Test
     fun `get interest rent failed test`() {
         `when`(getInterestRentUseCase.create(Unit))
             .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

         viewModel.getRentInterest()

         viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
     }
}