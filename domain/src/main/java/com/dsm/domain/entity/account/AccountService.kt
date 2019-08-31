package com.dsm.domain.entity.account

import io.reactivex.Flowable
import retrofit2.Response

interface AccountService {
    fun login(body: Any): Flowable<Response<Map<String, Any>>>

    fun getUserNick(): Flowable<String>

    fun changeUserNick(newNick: String): Flowable<Response<Unit>>
}