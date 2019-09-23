package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable

class ModifyRentUseCase(private val rentRepository: RentRepository) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        rentRepository.modifyRent(data)

}