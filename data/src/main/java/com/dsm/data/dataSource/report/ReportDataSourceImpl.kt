package com.dsm.data.dataSource.report

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import retrofit2.Response

class ReportDataSourceImpl(private val api: Api) : ReportDataSource {

    override fun reportPost(params: Any): Flowable<Response<Unit>> =
        api.reportPost(params).addSchedulers()

    override fun reportComment(params: Any): Flowable<Response<Unit>> =
        api.reportComment(params).addSchedulers()

}