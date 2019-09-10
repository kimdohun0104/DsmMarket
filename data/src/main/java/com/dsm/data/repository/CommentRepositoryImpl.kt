package com.dsm.data.repository

import com.dsm.data.dataSource.comment.CommentDataSource
import com.dsm.domain.repository.CommentRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class CommentRepositoryImpl(private val commentDataSource: CommentDataSource) : CommentRepository {
    override fun postComment(params: Any): Flowable<Unit> =
        commentDataSource.postComment(params).map { if (it.code() != 200) throw HttpException(it) }
}