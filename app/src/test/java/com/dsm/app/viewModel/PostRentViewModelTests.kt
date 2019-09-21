package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.jraska.livedata.test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PostRentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postRentUseCase: PostRentUseCase

    private lateinit var viewModel: PostRentViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = PostRentViewModel(postRentUseCase)
    }

    @Test
    fun `when there's no blank isPostEnable == true`() {
        viewModel.title.value = "TITLE"
        viewModel.price.value = "PRICE"
        viewModel.photo.value = "PHOTO"
        viewModel.content.value = "CONTENT"
        viewModel.category.value = "CATEGORY"

        assertTrue(viewModel.isPostEnable.test().value())
    }

    @Test
    fun `when there's blank isPostEnable == false`() {
        viewModel.title.value = "TITLE"

        assertFalse(viewModel.isPostEnable.test().value())
    }

    @Test
    fun `when time selected`() {
        viewModel.startHour.value = "1"
        viewModel.startMinute.value = "30"
        viewModel.endHour.value = "1"
        viewModel.endMinute.value = "00"
    }
}