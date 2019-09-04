package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.PostRentUseCase
import com.dsm.dsmmarketandroid.presentation.ui.post.postRent.PostRentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.io.File

class PostRentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var postRentUseCase: PostRentUseCase
    private lateinit var viewModel: PostRentViewModel

    @Before
    fun init() {
        postRentUseCase = mock(PostRentUseCase::class.java)
        viewModel = PostRentViewModel(postRentUseCase)
    }

    @Test
    fun `when there's no blank isPostEnable == true`() {
        viewModel.title.value = "TITLE"
        viewModel.price.value = "PRICE"
        viewModel.photo.value = "PHOTO"
        viewModel.content.value = "CONTENT"
        viewModel.tag.value = "TAG"
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