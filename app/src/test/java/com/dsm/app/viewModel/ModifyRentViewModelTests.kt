package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.ModifyRentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.modify.rent.ModifyRentViewModel
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

class ModifyRentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getRentDetailUseCase: GetRentDetailUseCase

    @Mock
    private lateinit var modifyRentUseCase: ModifyRentUseCase

    private val rentDetailModelMapper = RentDetailModelMapper()

    private lateinit var viewModel: ModifyRentViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = ModifyRentViewModel(getRentDetailUseCase, modifyRentUseCase, rentDetailModelMapper)
    }

    @Test
    fun `when there's blank isModifyEnable == false`() {
        viewModel.run {
            title.value = "TITLE"
            price.value = "PRICE"
            photo.value = "PHOTO"
            content.value = ""
            category.value = ""
        }

        assertFalse(viewModel.isModifyEnable.test().value())
    }

    @Test
    fun `when there's no blank isModifyEnable == true`() {
        viewModel.run {
            title.value = "TITLE"
            price.value = "PRICE"
            photo.value = "PHOTO"
            content.value = "CONTENT"
            category.value = "CATEGORY"
        }

        assertTrue(viewModel.isModifyEnable.test().value())
    }

    @Test
    fun modifyRentSuccess() {
        viewModel.run {
            title.value = "TITLE"
            content.value = "CONTENT"
            price.value = "PRICE"
            category.value = "CATEGORY"
            rentTime.value = "POSSIBLE_TIME"

            `when`(
                modifyRentUseCase.create(
                    hashMapOf(
                        "postId" to 0,
                        "title" to title.value,
                        "content" to content.value,
                        "price" to unit.value + "/" + price.value,
                        "category" to category.value,
                        "possible_time" to rentTime.value
                    )
                )
            ).thenReturn(Flowable.just(Unit))

            modifyRent(0)
            viewModel.finishActivityEvent.test().assertHasValue()
        }
    }
}