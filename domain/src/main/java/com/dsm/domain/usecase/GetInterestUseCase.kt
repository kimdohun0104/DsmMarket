package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.Product
import com.dsm.domain.repository.InterestRepository
import io.reactivex.Flowable

class GetInterestUseCase(private val interestRepository: InterestRepository) : UseCase<Int, List<Product>>() {
    override fun create(data: Int): Flowable<List<Product>> =
        interestRepository.getInterest(data)

}
