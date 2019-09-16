package com.dsm.data.repository

import com.dsm.data.dataSource.myPost.MyPostDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.MyPostRepository
import io.reactivex.Flowable

class MyPostRepositoryImpl(private val myPostDataSource: MyPostDataSource) : MyPostRepository {

    private val productMapper = ProductMapper()

    override fun getMyPurchase(): Flowable<List<Product>> =
        myPostDataSource.getMyPurchase().map(productMapper::mapFrom)

    override fun getMyRent(): Flowable<List<Product>> =
        myPostDataSource.getMyRent().map(productMapper::mapFrom)

}