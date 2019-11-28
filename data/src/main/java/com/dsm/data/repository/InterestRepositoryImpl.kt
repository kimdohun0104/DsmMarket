package com.dsm.data.repository

import com.dsm.data.dataSource.interest.InterestDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.InterestRepository
import io.reactivex.Flowable

class InterestRepositoryImpl(private val interestDataSource: InterestDataSource) : InterestRepository {

    private val productMapper = ProductMapper()

    override fun getInterest(type: Int): Flowable<List<Product>> =
        interestDataSource.getInterest(type).map(productMapper::mapFrom)
}