package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable

class GetRentDetailUseCase(private val rentRepository: RentRepository) : UseCase<Int, RentDetail>() {
    override fun create(data: Int): Flowable<RentDetail> =
        rentRepository.getRentDetail(data)

}