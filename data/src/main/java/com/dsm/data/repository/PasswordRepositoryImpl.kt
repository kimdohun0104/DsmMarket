package com.dsm.data.repository

import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class PasswordRepositoryImpl(private val passwordDataSource: PasswordDataSource) : PasswordRepository {

    override fun confirmPassword(password: String): Flowable<Unit> =
        passwordDataSource.confirmPassword(password).map {
            if (it.code() != 200) throw HttpException(it)
        }

    override fun changePassword(password: String): Flowable<Unit> =
        passwordDataSource.changePassword(password).map {
            if (it.code() != 200) throw HttpException(it)
        }

}