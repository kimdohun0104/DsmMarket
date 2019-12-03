package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.service.ProductService
import io.reactivex.Flowable

class GetMyPurchaseUseCase(private val productService: ProductService) : UseCase<Unit, List<Product>>() {
    override fun create(data: Unit): Flowable<List<Product>> =
        productService.getMyPurchase()

}