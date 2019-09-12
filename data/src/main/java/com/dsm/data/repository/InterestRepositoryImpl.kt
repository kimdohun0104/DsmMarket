package com.dsm.data.repository

import com.dsm.data.dataSource.interest.InterestDataSource
import com.dsm.domain.repository.InterestRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class InterestRepositoryImpl(private val interestDataSource: InterestDataSource) : InterestRepository {
    override fun interest(postId: Int, type: Int): Flowable<Unit> =
        interestDataSource.interest(postId, type).map { if (it.code() != 200) throw HttpException(it) }

    override fun unInterest(postId: Int, type: Int): Flowable<Unit> =
        interestDataSource.unInterest(postId, type).map { if (it.code() != 200) throw HttpException(it) }

}