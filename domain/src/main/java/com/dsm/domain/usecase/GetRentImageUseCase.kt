package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.RentRepositoryBefore
import io.reactivex.Flowable

class GetRentImageUseCase(private val rentRepository: RentRepositoryBefore) : UseCase<Int, String>() {
    override fun create(data: Int): Flowable<String> =
        rentRepository.getRentImage(data)

}