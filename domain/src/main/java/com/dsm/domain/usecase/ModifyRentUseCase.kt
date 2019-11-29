package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.RentRepositoryBefore
import io.reactivex.Flowable

class ModifyRentUseCase(private val rentRepository: RentRepositoryBefore) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        rentRepository.modifyRent(data)

}