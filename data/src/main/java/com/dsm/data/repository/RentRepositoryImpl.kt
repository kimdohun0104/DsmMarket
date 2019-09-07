package com.dsm.data.repository

import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable

class RentRepositoryImpl(private val rentDataSource: RentDataSource) : RentRepository {

    private val productMapper = ProductMapper()

    override fun getRentList(page: Int, pageSize: Int): Flowable<List<Product>> =
        rentDataSource.getRemoteRentList(page, pageSize).map(productMapper::mapFrom)

}