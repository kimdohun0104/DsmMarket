package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.entity.PostCategory
import com.dsm.domain.usecase.GetPostCategoryUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.postCategory.PostCategoryViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class PostCategoryViewModelTests : BaseTest() {

    @Mock
    private lateinit var getPostCategoryUseCase: GetPostCategoryUseCase

    private val postCategoryModelMapper = PostCategoryModelMapper()

    private lateinit var viewModel: PostCategoryViewModel

    @Before
    fun init() {
        viewModel = PostCategoryViewModel(getPostCategoryUseCase, postCategoryModelMapper)
    }

    @Test
    fun getPostCategorySuccess() {
        val response = arrayListOf(
            PostCategory(parent = "PARENT", child = listOf("child", "child"))
        )
        `when`(getPostCategoryUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getPostCategory()

        val afterMap = postCategoryModelMapper.mapFrom(response)

        viewModel.categoryList.test().assertValue { it.containsAll(afterMap) }
    }

    @Test
    fun `get post category failed test`() {
        `when`(getPostCategoryUseCase.create(Unit))
            .thenReturn(Flowable.error(Exception()))

        viewModel.run {
            getPostCategory()

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }

    @Test
    fun `selectCategory test`() {
        viewModel.run {
            selectCategory("CATEGORY")

            selectedCategory.test().assertValue("CATEGORY")
        }
    }
}