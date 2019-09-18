package com.dsm.data.repository

import com.dsm.data.dataSource.report.ReportDataSource
import com.dsm.domain.repository.ReportRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class ReportRepositoryImpl(private val reportDataSource: ReportDataSource) : ReportRepository {
    override fun reportPost(params: Any): Flowable<Unit> =
        reportDataSource.reportPost(params).map { if (it.code() != 200) throw HttpException(it) }

    override fun reportComment(params: Any): Flowable<Unit> =
        reportDataSource.reportComment(params).map { if (it.code() != 200) throw HttpException(it) }

}