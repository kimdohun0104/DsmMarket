package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.MyPostRepository
import io.reactivex.Flowable

class CompletePurchaseUseCase(private val myPostRepository: MyPostRepository) : UseCase<Int, Unit>() {
    override fun create(data: Int): Flowable<Unit> =
        myPostRepository.completePurchase(data)

}