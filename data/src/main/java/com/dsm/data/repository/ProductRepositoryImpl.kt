package com.dsm.data.repository

import com.dsm.data.dataSource.product.ProductDataSource
import com.dsm.data.mapper.InterestProductMapper
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class ProductRepositoryImpl(
    private val dataSource: ProductDataSource,
    private val productMapper: ProductMapper,
    private val interestProductMapper: InterestProductMapper
) : ProductRepository {

    override fun getRemotePurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        dataSource.getRemotePurchaseList(page, pageSize, search, category).map(productMapper::mapFrom)

    override fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        dataSource.getRemoteRentList(page, pageSize, search, category).map(productMapper::mapFrom)

    override fun getLocalProductList(page: Int, pageSize: Int, type: Int): List<Product> =
        dataSource.getLocalProductList(page, pageSize, type).map(productMapper::mapFrom)

    override fun addLocalProductList(list: List<Product>, type: Int): Completable =
        dataSource.addLocalProductList(productMapper.mapFrom(list, type))

    override fun getRemoteInterestProduct(type: Int): Flowable<List<Product>> =
        dataSource.getRemoteInterestProduct(type).map(interestProductMapper::mapFrom)

    override fun getLocalInterestProduct(type: Int): List<Product> =
        dataSource.getLocalInterestProduct(type).map(interestProductMapper::mapFrom)

    override fun addLocalInterestProduct(interestProduct: List<Product>, type: Int): Completable =
        dataSource.addLocalInterestProduct(interestProductMapper.mapFrom(interestProduct, type))

}