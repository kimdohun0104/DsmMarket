package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.domain.usecase.PostCommentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.addComment.AddCommentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class AddCommentViewModelTests : BaseTest() {

    @Mock
    private lateinit var postCommentUseCase: PostCommentUseCase

    private lateinit var viewModel: AddCommentViewModel

    @Before
    fun init() {
        viewModel = AddCommentViewModel(postCommentUseCase)
    }

    @Test
    fun `add comment button enable test`() {
        viewModel.content.value = "CONTENT"

        assertTrue(viewModel.isAddCommentEnable.test().value())
    }

    @Test
    fun `add comment button disable test`() {
        viewModel.content.value = ""

        assertFalse(viewModel.isAddCommentEnable.test().value())
    }

    @Test
    fun `add comment success test`() {
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

    @Test
    fun `add comment failed test`() {
        viewModel.run {
            content.value = "CONTENT"

            `when`(
                postCommentUseCase.create(
                    hashMapOf(
                        "postId" to 0,
                        "type" to 0,
                        "content" to content.value
                    )
                )
            ).thenReturn(Flowable.error(Exception()))

            postComment(0, 0)

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }
}