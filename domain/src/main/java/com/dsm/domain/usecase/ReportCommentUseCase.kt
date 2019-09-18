package com.dsm.domain.usecase

import com.dsm.domain.base.UseCase
import com.dsm.domain.repository.ReportRepository
import io.reactivex.Flowable

class ReportCommentUseCase(private val reportRepository: ReportRepository) : UseCase<Any, Unit>() {
    override fun create(data: Any): Flowable<Unit> =
        reportRepository.reportComment(data)
}