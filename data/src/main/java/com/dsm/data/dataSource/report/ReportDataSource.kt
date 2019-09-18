package com.dsm.data.dataSource.report

import io.reactivex.Flowable
import retrofit2.Response

interface ReportDataSource {
    fun reportPost(params: Any): Flowable<Response<Unit>>

    fun reportComment(params: Any): Flowable<Response<Unit>>
}