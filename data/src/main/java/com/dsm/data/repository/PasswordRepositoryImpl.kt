package com.dsm.data.repository

import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable
import retrofit2.Response

class PasswordRepositoryImpl(private val passwordDataSource: PasswordDataSource) : PasswordRepository {

    override fun sendPasswordCode(email: String): Flowable<Int> =
        passwordDataSource.sendPasswordCode(email).map { it.code() }

    override fun passwordCodeConfirm(body: Any): Flowable<Response<Map<String, Int>>> =
        passwordDataSource.passwordCodeConfirm(body)

    override fun changePassword(params: Any): Flowable<Int> =
        passwordDataSource.changePassword(params).map { it.code() }

}