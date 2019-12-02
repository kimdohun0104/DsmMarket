package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.error.Resource
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class ModifyRentUseCase(private val detailService: DetailService) : UseCase<Any, Resource<Unit>>() {
    override fun create(data: Any): Flowable<Resource<Unit>> =
        detailService.modifyRent(data)

}