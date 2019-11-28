package com.dsm.data.repository

import com.dsm.data.dataSource.detail.DetailDataSource
import com.dsm.data.mapper.PurchaseDetailMapper
import com.dsm.data.mapper.RecommendMapper
import com.dsm.data.mapper.RentDetailMapper
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.repository.DetailRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailRepositoryImpl(
    private val dataSource: DetailDataSource,
    private val purchaseDetailMapper: PurchaseDetailMapper,
    private val rentDetailMapper: RentDetailMapper,
    private val recommendMapper: RecommendMapper
) : DetailRepository {

    override fun getRemotePurchaseDetail(postId: Int): Flowable<PurchaseDetail> =
        dataSource.getRemotePurchaseDetail(postId).map(purchaseDetailMapper::mapFrom)

    override fun getLocalPurchaseDetail(postId: Int): PurchaseDetail =
        purchaseDetailMapper.mapFrom(dataSource.getLocalPurchaseDetail(postId))

    override fun addLocalPurchaseDetail(purchaseDetail: PurchaseDetail): Completable =
        dataSource.addLocalPurchaseDetail(purchaseDetailMapper.mapFrom(purchaseDetail))

    override fun getRemoteRentDetail(postId: Int): Flowable<RentDetail> =
        dataSource.getRemoteRentDetail(postId).map(rentDetailMapper::mapFrom)

    override fun getLocalRentDetail(postId: Int): RentDetail =
        rentDetailMapper.mapFrom(dataSource.getLocalRentDetail(postId))

    override fun addLocalRentDetail(rentDetail: RentDetail): Completable =
        dataSource.addLocalRentDetail(rentDetailMapper.mapFrom(rentDetail))

    override fun interest(postId: Int, type: Int): Flowable<Unit> =
        dataSource.interest(postId, type)

    override fun unInterest(postId: Int, type: Int): Flowable<Unit> =
        dataSource.unInterest(postId, type)

    override fun getRecommendProduct(): Flowable<List<Recommend>> =
        dataSource.getRecommendProduct().map(recommendMapper::mapFrom)

    override fun getRelatedProduct(postId: Int, type: Int): Flowable<List<Recommend>> =
        dataSource.getRelatedProduct(postId, type).map(recommendMapper::mapFrom)
}