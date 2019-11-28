package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.usecase.PostPurchaseUseCase
import com.dsm.dsmmarketandroid.presentation.ui.main.post.postPurchase.PostPurchaseViewModel
import com.jraska.livedata.test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostPurchaseViewModelTests : BaseTest() {

    @Mock
    private lateinit var postPurchaseUseCase: PostPurchaseUseCase

    private lateinit var viewModel: PostPurchaseViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = PostPurchaseViewModel(postPurchaseUseCase)
    }

    @Test
    fun `setImageList test`() {
        val imageList = arrayListOf("IMAGE_LIST")
        viewModel.setImageList(imageList)

        viewModel.imageList.test().assertValue(imageList)
    }

    @Test
    fun `setCategory test`() {
        val category = "CATEGORY"
        viewModel.setCategory(category)

        viewModel.category.test().assertValue(category)
    }

    @Test
    fun `post button enable test`() {
        viewModel.run {
            setCategory("CATEGORY")
            setImageList(arrayListOf("IMAGE_LIST"))
            title.value = "TITLE"
            price.value = "PRICE"
            content.value = "CONTENT"

            assertTrue(isPostEnable.test().value())
        }
    }

    @Test
    fun `post button disable test`() {
        viewModel.run {
            setCategory("")
            setImageList(arrayListOf())
            title.value = ""
            price.value = ""
            content.value = ""

            assertFalse(isPostEnable.test().value())
        }
    }

    @Test
    fun `isCategorySelected true test`() {
        viewModel.setCategory("CATEGORY")

        assertTrue(viewModel.isCategorySelected.test().value())
    }

    @Test
    fun `isCategorySelected false test`() {
        viewModel.setCategory("")

        assertFalse(viewModel.isCategorySelected.test().value())
    }
}