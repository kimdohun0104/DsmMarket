package com.dsm.domain.service

import com.dsm.domain.entity.PurchaseDetail
import io.reactivex.Flowable

interface DetailService {

    fun getPurchaseDetail(postId: Int): Flowable<PurchaseDetail>
}