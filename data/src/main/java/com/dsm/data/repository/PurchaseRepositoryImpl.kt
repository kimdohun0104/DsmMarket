package com.dsm.data.repository

import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.mapper.PurchaseMapper
import com.dsm.domain.entity.Purchase
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable

class PurchaseRepositoryImpl(private val purchaseDataSource: PurchaseDataSource) : PurchaseRepository {

    private val postMapper = PurchaseMapper()

    override fun getPurchaseList(page: Int, pageSize: Int): Flowable<List<Purchase>> =
        purchaseDataSource.getRemotePurchaseList(page, pageSize).map(postMapper::mapFrom)

}