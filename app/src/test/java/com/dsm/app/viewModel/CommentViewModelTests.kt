package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.entity.Comment
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.GetCommentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.comment.CommentViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class CommentViewModelTests : BaseTest() {

    @Mock
    private lateinit var getCommentUseCase: GetCommentUseCase

    private val commentModelMapper = CommentModelMapper()

    private lateinit var viewModel: CommentViewModel

    @Before
    fun init() {
        viewModel = CommentViewModel(getCommentUseCase, commentModelMapper)
    }

    @Test
    fun getCommentSuccess() {
        val response = arrayListOf(
            Comment("nick", "content", "createdAt", false),
            Comment("nick", "content", "createdAt", false),
            Comment("nick", "content", "createdAt", false),
            Comment("nick", "content", "createdAt", false),
            Comment("nick", "content", "createdAt", false)
        ) as List<Comment>
        `when`(getCommentUseCase.create(GetCommentUseCase.Params(0, 0))).thenReturn(Flowable.just(Resource.Success(response)))

        viewModel.run {
            getCommentList(0, 0)

            listItems.test().assertValue(commentModelMapper.mapFrom(response))
            commentCount.test().assertValue(response.size)
        }
    }

    @Test
    fun `get comment list failed test`() {
        `when`(getCommentUseCase.create(GetCommentUseCase.Params(0, 0)))
            .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

        viewModel.run {
            getCommentList(0, 0)

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }

    @Test
    fun reportCommentTest() {
        viewModel.reportComment("NICK")

        viewModel.dialogReportComment.test().assertValue("NICK")
    }
}