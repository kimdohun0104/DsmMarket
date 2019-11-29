package com.dsm.data.repository

import com.dsm.data.dataSource.purchaseBefore.PurchaseDataSourceBefore
import com.dsm.domain.repository.PurchaseRepositoryBefore
import io.reactivex.Flowable
import retrofit2.HttpException

class PurchaseRepositoryImplBefore(private val purchaseDataSource: PurchaseDataSourceBefore) : PurchaseRepositoryBefore {

    override fun modifyPurchase(params: Any): Flowable<Unit> =
        purchaseDataSource.modifyPurchase(params).map { if (it.code() != 200) throw HttpException(it) }

    override fun getPurchaseImage(postId: Int): Flowable<List<String>> =
        purchaseDataSource.getPurchaseImage(postId).map { it.images }
}