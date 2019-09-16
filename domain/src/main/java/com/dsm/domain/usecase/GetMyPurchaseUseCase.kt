package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.MyPostRepository
import io.reactivex.Flowable

class GetMyPurchaseUseCase(private val myPostRepository: MyPostRepository) : UseCase<Unit, List<Product>>() {
    override fun create(data: Unit): Flowable<List<Product>> =
        myPostRepository.getMyPurchase()

}