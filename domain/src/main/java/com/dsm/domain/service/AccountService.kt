package com.dsm.domain.service

import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface AccountService {

    fun sendTempPassword(email: String): Flowable<Resource<Unit>>
}