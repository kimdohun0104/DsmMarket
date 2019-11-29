package com.dsm.data.repository

import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.mapper.InterestProductMapper
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.RentRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class RentRepositoryImpl(
    private val dataSource: RentDataSource,
    private val productMapper: ProductMapper,
    private val interestProductMapper: InterestProductMapper
) : RentRepository {

    override fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        dataSource.getRemoteRentList(page, pageSize, search, category).map(productMapper::mapFrom)

    override fun getLocalRentList(page: Int, pageSize: Int): List<Product> =
        dataSource.getLocalRentList(page, pageSize).map(productMapper::mapFrom)

    override fun addLocalRentList(list: List<Product>): Completable =
        dataSource.addLocalRentList(productMapper.mapFrom(list, 1))

    override fun getRemoteInterestRent(): Flowable<List<Product>> =
        dataSource.getRemoteInterestRent().map(productMapper::mapFrom)

    override fun getLocalInterestRent(): List<Product> =
        dataSource.getLocalInterestRent().map { interestProductMapper.mapFrom(it) }

    override fun addLocalInterestRent(interestRent: List<Product>): Completable =
        dataSource.addLocalInterestRent(interestProductMapper.mapFrom(interestRent, 1))


}