package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.data.error.exception.InternalException
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.Success
import com.dsm.domain.usecase.GetRentDetailUseCase
import com.dsm.domain.usecase.ModifyRentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.RentDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.modifyRent.ModifyRentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ModifyRentViewModelTests : BaseTest() {

    @Mock
    private lateinit var getRentDetailUseCase: GetRentDetailUseCase

    @Mock
    private lateinit var modifyRentUseCase: ModifyRentUseCase

    private val rentDetailModelMapper = RentDetailModelMapper()

    private lateinit var viewModel: ModifyRentViewModel

    @Before
    fun init() {
        viewModel = ModifyRentViewModel(getRentDetailUseCase, modifyRentUseCase, rentDetailModelMapper)
    }

    @Test
    fun `modify button disable test`() {
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
    fun `modify button enable test`() {
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
    fun `rentTime format test`() {
        viewModel.run {
            startHour.value = "10"
            startMinute.value = "30"
            endHour.value = "11"
            endMinute.value = "30"

            rentTime.test().assertValue(startHour.value + ":" + startMinute.value + "~" + endHour.value + ":" + endMinute.value)
        }
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

    @Test
    fun `get rent detail success test`() {
        val response = RentDetail(
            title = "TITLE",
            id = 0,
            commentCount = 1,
            author = "AUTHOR",
            content = "CONTENT",
            category = "CATEGORY",
            img = "IMG",
            createdAt = "CREATED_AT",
            price = "1회 당 1000원",
            isInterest = true,
            possibleTime = "POSSIBLE_TIME",
            isMe = false
        )
        `when`(getRentDetailUseCase.create(0))
            .thenReturn(Flowable.just(Success(response)))

        val mapped = rentDetailModelMapper.mapFrom(response)

        viewModel.run {
            getRentDetail(0)

            title.test().assertValue(mapped.title)
            price.test().assertValue(mapped.price.split(" ")[2].substring(0, mapped.price.split(" ")[2].length - 1))
            photo.test().assertValue(mapped.img)
            content.test().assertValue(mapped.content)
            category.test().assertValue(mapped.category)
            rentTime.test().assertValue(mapped.possibleTime)
        }
    }

    @Test
    fun `get rent detail failed test`() {
        `when`(getRentDetailUseCase.create(0))
            .thenReturn(Flowable.error(InternalException(createHttpException(500))))

        viewModel.getRentDetail(0)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }

    @Test
    fun `modify rent success test`() {
        viewModel.run {
            title.value = "TITLE"
            content.value = "CONTENT"
            unit.value = "1회 당"
            price.value = "100원"
            category.value = "CATEGORY"
            startHour.value = "10"
            startMinute.value = "30"
            endHour.value = "11"
            endMinute.value = "30"

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

            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun selectPriceUnitTest() {
        viewModel.run {
            selectPriceUnit(0)
            unit.test().assertValue("0")
        }
    }

    @Test
    fun setCategoryTest() {
        viewModel.run {
            setCategory("CATEGORY")
            category.test().assertValue("CATEGORY")
        }
    }
}