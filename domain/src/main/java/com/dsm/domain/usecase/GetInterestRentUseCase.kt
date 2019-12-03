package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.error.Resource
import com.dsm.domain.service.ProductService
import io.reactivex.Flowable

class GetInterestRentUseCase(private val productService: ProductService) : UseCase<Unit, Resource<List<Product>>>() {
    override fun create(data: Unit): Flowable<Resource<List<Product>>> =
        productService.getInterest(1)

}