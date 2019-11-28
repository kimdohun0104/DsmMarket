package com.dsm.data.repository

import com.dsm.data.dataSource.purchaseBefore.PurchaseDataSourceBefore
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.PurchaseRepositoryBefore
import io.reactivex.Flowable
import retrofit2.HttpException

class PurchaseRepositoryImplBefore(private val purchaseDataSource: PurchaseDataSourceBefore) : PurchaseRepositoryBefore {

    private val productMapper = ProductMapper()

    override fun getPurchaseList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        purchaseDataSource.getPurchaseList(page, pageSize, search, category).map(productMapper::mapFrom)
            .doOnNext { if (search.isNotBlank()) purchaseDataSource.addSearchHistory(search).subscribe() }

    override fun modifyPurchase(params: Any): Flowable<Unit> =
        purchaseDataSource.modifyPurchase(params).map { if (it.code() != 200) throw HttpException(it) }

    override fun getPurchaseImage(postId: Int): Flowable<List<String>> =
        purchaseDataSource.getPurchaseImage(postId).map { it.images }
}