package com.dsm.domain.repository

import io.reactivex.Flowable

interface ReportRepository {

    fun reportPost(params: Any): Flowable<Unit>

    fun reportComment(params: Any): Flowable<Unit>
}