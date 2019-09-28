package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable

class GetRentImageUseCase(private val rentRepository: RentRepository) : UseCase<Int, String>() {
    override fun create(data: Int): Flowable<String> =
        rentRepository.getRentImage(data)

}