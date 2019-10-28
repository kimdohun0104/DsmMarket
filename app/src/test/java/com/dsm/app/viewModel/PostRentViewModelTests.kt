package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.jraska.livedata.test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class PostRentViewModelTests : BaseTest() {

    @Mock
    private lateinit var postRentUseCase: PostRentUseCase

    private lateinit var viewModel: PostRentViewModel

    @Before
    fun init() {
        viewModel = PostRentViewModel(postRentUseCase)
    }

    @Test
    fun `post button enable test`() {
        viewModel.run {
            title.value = "TITLE"
            price.value = "PRICE"
            photo.value = "PHOTO"
            content.value = "CONTENT"
            category.value = "CATEGORY"
        }

        assertTrue(viewModel.isPostEnable.test().value())
    }

    @Test
    fun `post button disable test`() {
        viewModel.title.value = "TITLE"

        assertFalse(viewModel.isPostEnable.test().value())
    }

    @Test
    fun `rentTime format test`() {
        viewModel.run {
            startHour.value = "10"
            startMinute.value = "30"
            endHour.value = "11"
            endMinute.value = "30"
        }

        viewModel.rentTime.test().assertValue(viewModel.startHour.value + ":" + viewModel.startMinute.value + "~" + viewModel.endHour.value + ":" + viewModel.endMinute.value)
    }

    @Test
    fun `setPhoto test`() {
        viewModel.setPhoto("PHOTO_URL")

        viewModel.photo.test().assertValue("PHOTO_URL")
    }

    @Test
    fun `setCategory test`() {
        viewModel.setCategory("CATEGORY")

        viewModel.category.test().assertValue("CATEGORY")
    }
}