package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.error.Resource
import com.dsm.domain.service.DetailService
import io.reactivex.Flowable

class GetRentDetailUseCase(private val detailService: DetailService) : UseCase<Int, Resource<RentDetail>>() {
    override fun create(data: Int): Flowable<Resource<RentDetail>> =
        detailService.getRentDetail(data)

}