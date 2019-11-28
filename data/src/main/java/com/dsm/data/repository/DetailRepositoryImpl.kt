package com.dsm.data.repository

import com.dsm.data.dataSource.detail.DetailDataSource
import com.dsm.data.mapper.PurchaseDetailMapper
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.repository.DetailRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailRepositoryImpl(
    private val dataSource: DetailDataSource,
    private val purchaseDetailMapper: PurchaseDetailMapper
) : DetailRepository {

    override fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetail> =
        dataSource.getRemotePurchaseDetail(postId).map(purchaseDetailMapper::mapFrom)

    override fun getLocalPurchaseDetail(postId: Int): PurchaseDetail =
        purchaseDetailMapper.mapFrom(dataSource.getLocalPurchaseDetail(postId))

    override fun addLocalPurchaseDetail(purchaseDetail: PurchaseDetail): Completable =
        dataSource.addLocalPurchaseDetail(purchaseDetailMapper.mapFrom(purchaseDetail))


}