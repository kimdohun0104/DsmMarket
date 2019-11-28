package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetPurchaseDetailUseCase
import com.dsm.domain.usecase.ModifyPurchaseUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.modifyPurchase.ModifyPurchaseViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ModifyPurchaseViewModelTests : BaseTest() {

    @Mock
    private lateinit var getPurchaseDetailUseCase: GetPurchaseDetailUseCase

    @Mock
    private lateinit var modifyPurchaseUseCase: ModifyPurchaseUseCase

    private val purchaseDetailModelMapper = PurchaseDetailModelMapper()

    private lateinit var viewModel: ModifyPurchaseViewModel

    @Before
    fun init() {
        viewModel =
            ModifyPurchaseViewModel(getPurchaseDetailUseCase, modifyPurchaseUseCase, purchaseDetailModelMapper)
    }

    @Test
    fun `modify button disable test`() {
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
    fun `modify button enable test`() {
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
    fun `get purchase detail success test`() {
        val response = PurchaseDetail(
            title = "TITLE",
            isInterest = true,
            price = "PRICE원",
            createdAt = "CREATED_AT",
            img = listOf(),
            category = "CATEGORY",
            content = "CONTENT",
            author = "AUTHOR",
            commentCount = 0,
            id = 0,
            isMe = false
        )
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(Resource.Success(response)))

        val mapped = purchaseDetailModelMapper.mapFrom(response)

        viewModel.run {
            getPurchaseDetail(0)

            title.test().assertValue(mapped.title)
            price.test().assertValue(mapped.price.substring(0, mapped.price.length - 1))
            category.test().assertValue(mapped.category)
            content.test().assertValue(mapped.content)
            imageList.test().assertValue(arrayListOf<String>().apply {
                mapped.img.forEach { add(it) }
            })
        }
    }

    @Test
    fun `get purchase detail failed test`() {
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

        viewModel.run {
            getPurchaseDetail(0)

            toastEvent.test().assertValue(R.string.fail_server_error)
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

    @Test
    fun `setCategory test`() {
        viewModel.setCategory("CATEGORY")

        viewModel.category.test().assertValue("CATEGORY")
    }
}