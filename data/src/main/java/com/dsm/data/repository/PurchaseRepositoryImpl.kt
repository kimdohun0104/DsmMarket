package com.dsm.data.repository

import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable

class PurchaseRepositoryImpl(private val purchaseDataSource: PurchaseDataSource) : PurchaseRepository {

    private val productMapper = ProductMapper()

    override fun getPurchaseList(page: Int, pageSize: Int): Flowable<List<Product>> =
        purchaseDataSource.getRemotePurchaseList(page, pageSize).map(productMapper::mapFrom)

}