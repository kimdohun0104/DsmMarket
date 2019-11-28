package com.dsm.data.repository

import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class RentRepositoryImpl(private val rentDataSource: RentDataSource) : RentRepository {

    private val productMapper = ProductMapper()

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        rentDataSource.getRentList(page, pageSize, search, category).map(productMapper::mapFrom)
            .doOnNext { if (search.isNotBlank()) rentDataSource.addSearchHistory(search).subscribe() }

    override fun modifyRent(params: Any): Flowable<Unit> =
        rentDataSource.modifyRent(params).map { if (it.code() != 200) throw HttpException(it) }

    override fun getRentImage(postId: Int): Flowable<String> =
        rentDataSource.getRentImage(postId).map { it.image }
}