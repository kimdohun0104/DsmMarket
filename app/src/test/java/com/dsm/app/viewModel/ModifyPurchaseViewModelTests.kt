package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.ModifyPurchaseUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.modify.purchase.ModifyPurchaseViewModel
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

class ModifyPurchaseViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getPurchaseDetailUseCase: GetPurchaseDetailUseCase

    @Mock
    private lateinit var modifyPurchaseUseCase: ModifyPurchaseUseCase

    private val purchaseDetailModelMapper = PurchaseDetailModelMapper()

    private lateinit var viewModel: ModifyPurchaseViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = ModifyPurchaseViewModel(getPurchaseDetailUseCase, modifyPurchaseUseCase, purchaseDetailModelMapper)
    }

    @Test
    fun `when there's blank isModifyEnable == false`() {
        viewModel.run {
            title.value = ""
            price.value = "PRICE"
            category.value = ""
            content.value = "CONTENT"
            imageList.value = arrayListOf("", "")
        }

        assertFalse(viewModel.isModifyEnable.test().value())
    }

    @Test
    fun `when there's no blank isModifyEnable == true`() {
        viewModel.title.value = "TITLE"
        viewModel.run {
            title.value = "TITLE"
            price.value = "PRICE"
            category.value = "CATEGORY"
            content.value = "CONTENT"
            imageList.value = arrayListOf("ARRAY1", "ARRAY1")
        }

        assertTrue(viewModel.isModifyEnable.test().value())
    }

    @Test
    fun getPurchaseDetailSuccess() {
        val response = PurchaseDetail(
            id = 0,
            category = "CATEGORY",
            img = listOf("img1", "img2"),
            title = "TITLE",
            content = "CONTENT",
            createdAt = "CREATED_AT",
            price = "PRICE",
            isInterest = true,
            author = "AUTHOR",
            commentCount = 4
        )
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.getPurchaseDetail(0)

        val afterMap = purchaseDetailModelMapper.mapFrom(response)

        viewModel.run {
            title.test().assertValue(afterMap.title)
            price.test().assertValue(afterMap.price)
            category.test().assertValue(afterMap.category)
            content.test().assertValue(afterMap.content)
        }
    }

    @Test
    fun modifyPurchaseSuccess() {
        viewModel.run {
            title.value = "TITLE"
            content.value = "CONTENT"
            price.value = "PRICE"
            category.value = "CATEGORY"
        }
        `when`(
            modifyPurchaseUseCase.create(
                hashMapOf(
                    "postId" to 0,
                    "title" to viewModel.title.value,
                    "content" to viewModel.content.value,
                    "price" to viewModel.price.value,
                    "category" to viewModel.category.value
                )
            )
        ).thenReturn(Flowable.just(Unit))

        viewModel.modifyPurchase(0)

        viewModel.finishActivityEvent.test().assertHasValue()
    }
}