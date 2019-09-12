package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.PostCommentUseCase
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AddCommentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var postCommentUseCase: PostCommentUseCase
    private lateinit var viewModel: AddCommentViewModel

    @Before
    fun init() {
        postCommentUseCase = mock(PostCommentUseCase::class.java)
        viewModel = AddCommentViewModel(postCommentUseCase)
    }

    @Test
    fun `when there's no blank isAddCommentEnable == true`() {
        viewModel.content.value = "CONTENT"

        assertTrue(viewModel.isAddCommentEnable.test().value())
    }

    @Test
    fun `when there's blank isAddCommentEnable == false`() {
        viewModel.content.value = ""

        assertFalse(viewModel.isAddCommentEnable.test().value())
    }

    @Test
    fun whenAddCommentSuccess() {
        viewModel.content.value = "CONTENT"

        `when`(
            postCommentUseCase.create(
                hashMapOf(
                    "postId" to 0,
                    "type" to 0,
                    "content" to viewModel.content.value
                )
            )
        ).thenReturn(Flowable.just(Unit))

        viewModel.postComment(0, 0)

        viewModel.finishActivityEvent.test().assertHasValue()
    }
}