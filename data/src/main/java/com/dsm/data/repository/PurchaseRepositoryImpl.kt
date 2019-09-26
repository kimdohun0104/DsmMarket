package com.dsm.data.repository

import com.dsm.data.dataSource.purchase.PurchaseDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.data.mapper.PurchaseDetailMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.repository.PurchaseRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class PurchaseRepositoryImpl(private val purchaseDataSource: PurchaseDataSource) : PurchaseRepository {

    private val productMapper = ProductMapper()
    private val purchaseDetailMapper = PurchaseDetailMapper()

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        purchaseDataSource.getPurchaseList(page, pageSize, search, category).map(productMapper::mapFrom)
            .doOnNext { if (search.isNotBlank()) purchaseDataSource.addSearchHistory(search).subscribe() }

    override fun getPurchaseDetail(postId: Int): Flowable<PurchaseDetail> =
        purchaseDataSource.getRemotePurchaseDetail(postId).map {
            if (it.code() == 200) purchaseDetailMapper.mapFrom(it.body()!!)
            else throw HttpException(it)
        }
            .doOnNext { purchaseDataSource.addLocalPurchaseDetail(purchaseDetailMapper.mapFrom(it)).subscribe() }
            .onErrorReturn { purchaseDetailMapper.mapFrom(purchaseDataSource.getLocalPurchaseDetail(postId)) }

    override fun modifyPurchase(params: Any): Flowable<Unit> =
        purchaseDataSource.modifyPurchase(params).map { if (it.code() != 200) throw HttpException(it) }
}