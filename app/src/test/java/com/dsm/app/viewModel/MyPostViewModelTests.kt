package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Product
import com.dsm.domain.usecase.CompletePurchaseUseCase
import com.dsm.domain.usecase.CompleteRentUseCase
import com.dsm.domain.usecase.GetMyPurchaseUseCase
import com.dsm.domain.usecase.GetMyRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MyPostViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getMyPurchaseUseCase: GetMyPurchaseUseCase

    @Mock
    private lateinit var getMyRentUseCase: GetMyRentUseCase

    @Mock
    private lateinit var completePurchaseUseCase: CompletePurchaseUseCase

    @Mock
    private lateinit var completeRentUseCase: CompleteRentUseCase

    private val productModelMapper = ProductModelMapper()

    private lateinit var viewModel: MyPostViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = MyPostViewModel(getMyPurchaseUseCase, getMyRentUseCase, completePurchaseUseCase, completeRentUseCase, productModelMapper)
    }

    @Test
    fun getMyPurchase() {
        val response = listOf(Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        `when`(getMyPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getMyPurchase()

        viewModel.purchaseList.test().assertValue(productModelMapper.mapFrom(response))
    }

    @Test
    fun getMyRent() {
        val response = listOf(Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        `when`(getMyRentUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getMyRent()

        viewModel.rentList.test().assertValue(productModelMapper.mapFrom(response))
    }

    @Test
    fun completePurchaseSuccess() {
        val purchaseList = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        viewModel.purchaseList.value = purchaseList

        `when`(completePurchaseUseCase.create(purchaseList[0].postId))
            .thenReturn(Flowable.just(Unit))

        viewModel.completePurchase(0)

        viewModel.dismissEvent.test().assertHasValue()
        viewModel.deletePositionFromPurchase.test().assertValue(0)
    }

    @Test
    fun completeRentSuccess() {
        val rentList = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        viewModel.rentList.value = rentList

        `when`(completeRentUseCase.create(rentList[0].postId))
            .thenReturn(Flowable.just(Unit))

        viewModel.completeRent(0)

        viewModel.dismissEvent.test().assertHasValue()
        viewModel.deletePositionFromRent.test().assertValue(0)
    }
}