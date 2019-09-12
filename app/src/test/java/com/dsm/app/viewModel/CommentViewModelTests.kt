package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.entity.Comment
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.comment.CommentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CommentViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getCommentUseCase: GetCommentUseCase
    private lateinit var commentModelMapper: CommentModelMapper
    private lateinit var viewModel: CommentViewModel

    @Before
    fun init() {
        getCommentUseCase = mock(GetCommentUseCase::class.java)
        commentModelMapper = CommentModelMapper()
        viewModel = CommentViewModel(getCommentUseCase, commentModelMapper)
    }

    @Test
    fun getCommentSuccess() {
        val response = arrayListOf(
            Comment("nick", "content", "createdAt"),
            Comment("nick", "content", "createdAt"),
            Comment("nick", "content", "createdAt"),
            Comment("nick", "content", "createdAt"),
            Comment("nick", "content", "createdAt")
            )
        `when`(getCommentUseCase.create(GetCommentUseCase.Params(0, 0))).thenReturn(Flowable.just(response))

        viewModel.getCommentList(0, 0)

        viewModel.listItems.test().assertValue(commentModelMapper.mapFrom(response))
        viewModel.commentCount.test().assertValue(response.size)
    }
}