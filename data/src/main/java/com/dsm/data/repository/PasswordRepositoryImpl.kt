package com.dsm.data.repository

import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable

class PasswordRepositoryImpl(private val passwordDataSource: PasswordDataSource) : PasswordRepository {

    override fun sendPasswordCode(email: String): Flowable<Int> =
        passwordDataSource.sendPasswordCode(email).map { it.code() }

    override fun passwordCodeConfirm(body: Any): Flowable<Int> =
        passwordDataSource.passwordCodeConfirm(body).map { it.code() }

    override fun changePassword(newPassword: String): Flowable<Int> =
        passwordDataSource.changePassword(newPassword).map { it.code() }

    override fun changePassword(email: String, newPassword: String): Flowable<Int> =
        passwordDataSource.changePassword(email, newPassword).map { it.code() }

}