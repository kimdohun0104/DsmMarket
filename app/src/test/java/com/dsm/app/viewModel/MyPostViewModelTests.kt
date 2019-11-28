package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.entity.Product
import com.dsm.domain.usecase.CompletePurchaseUseCase
import com.dsm.domain.usecase.CompleteRentUseCase
import com.dsm.domain.usecase.GetMyPurchaseUseCase
import com.dsm.domain.usecase.GetMyRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import com.dsm.dsmmarketandroid.presentation.model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.main.me.myPost.MyPostViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.io.IOException

class MyPostViewModelTests : BaseTest() {

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
        viewModel = MyPostViewModel(
            getMyPurchaseUseCase,
            getMyRentUseCase,
            completePurchaseUseCase,
            completeRentUseCase,
            productModelMapper
        )
    }

    @Test
    fun `isPurchaseEmpty value test`() {
        viewModel.run {
            purchaseList.value = listOf(ProductModel(0, "", "", "", ""))
            assertFalse(isPurchaseEmpty.test().value())

            purchaseList.value = listOf()
            assertTrue(isPurchaseEmpty.test().value())
        }
    }

    @Test
    fun `isRentEmpty value test`() {
        viewModel.run {
            rentList.value = listOf(ProductModel(0, "", "", "", ""))
            assertFalse(isRentEmpty.test().value())

            rentList.value = listOf()
            assertTrue(isRentEmpty.test().value())
        }
    }

    @Test
    fun `get my purchase success test`() {
        val response = listOf(Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        `when`(getMyPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getMyPost(ProductType.PURCHASE)

        viewModel.run {
            purchaseList.test().assertValue(productModelMapper.mapFrom(response))
            isPurchaseRefreshing.test().assertValue(false)
            isPurchaseProgressVisible.test().assertValue(false)
        }
    }

    @Test
    fun `get my purchase failed test`() {
        `when`(getMyPurchaseUseCase.create(Unit))
            .thenReturn(Flowable.error(IOException()))

        viewModel.getMyPost(ProductType.PURCHASE)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }

    @Test
    fun `get my rent success test`() {
        val response = listOf(Product(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        `when`(getMyRentUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getMyPost(ProductType.RENT)

        viewModel.run {
            rentList.test().assertValue(productModelMapper.mapFrom(response))
            isRentRefreshing.test().assertValue(false)
            isRentProgressVisible.test().assertValue(false)
        }
    }

    @Test
    fun `get my rent failed test`() {
        `when`(getMyRentUseCase.create(Unit))
            .thenReturn(Flowable.error(IOException()))

        viewModel.getMyPost(ProductType.RENT)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }

    @Test
    fun completePurchaseSuccess() {
        val purchaseList = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        viewModel.purchaseList.value = purchaseList

        `when`(completePurchaseUseCase.create(purchaseList[0].postId))
            .thenReturn(Flowable.just(Unit))

        viewModel.completePost(0, ProductType.PURCHASE)

        viewModel.dismissEvent.test().assertHasValue()
        viewModel.deletePositionFromPurchase.test().assertValue(0)
    }

    @Test
    fun `complete purchase failed test`() {
        val purchaseList = listOf(ProductModel(0, "", "", "", ""))
        viewModel.purchaseList.value = purchaseList

        `when`(completePurchaseUseCase.create(purchaseList[0].postId))
            .thenReturn(Flowable.error(IOException()))

        viewModel.completePost(0, ProductType.PURCHASE)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }

    @Test
    fun completeRentSuccess() {
        val rentList = listOf(ProductModel(0, "TITLE", "IMG", "CREATED_AT", "PRICE"))
        viewModel.rentList.value = rentList

        `when`(completeRentUseCase.create(rentList[0].postId))
            .thenReturn(Flowable.just(Unit))

        viewModel.completePost(0, ProductType.RENT)

        viewModel.dismissEvent.test().assertHasValue()
        viewModel.deletePositionFromRent.test().assertValue(0)
    }

    @Test
    fun `complete rent failed test`() {
        val rentList = listOf(ProductModel(0, "", "", "", ""))
        viewModel.rentList.value = rentList

        `when`(completeRentUseCase.create(rentList[0].postId))
            .thenReturn(Flowable.error(IOException()))

        viewModel.completePost(0, ProductType.RENT)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }
}