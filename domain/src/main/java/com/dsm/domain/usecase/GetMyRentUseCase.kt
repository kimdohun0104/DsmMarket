package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.MyPostRepository
import io.reactivex.Flowable

class GetMyRentUseCase(private val myPostRepository: MyPostRepository) : UseCase<Unit, List<Product>>() {
    override fun create(data: Unit): Flowable<List<Product>> =
        myPostRepository.getMyRent()

}