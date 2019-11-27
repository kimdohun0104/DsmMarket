package com.dsm.domain.service

import com.dsm.domain.error.Resource
import io.reactivex.Flowable

interface AuthService {
    fun login(body: Any) : Flowable<Resource<Unit>>
}