package com.dsm.data.repository

import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class PasswordRepositoryImpl(private val passwordDataSource: PasswordDataSource) : PasswordRepository {

    override fun confirmPassword(password: String): Flowable<Pair<String, Int>> =
        passwordDataSource.confirmPassword(password).map {
            if (it.code() != 200) throw HttpException(it)
            val response = it.body()!!
            Pair(response["email"] ?: "", (response["authCode"] ?: "").toInt())
        }

    override fun changePassword(params: Any): Flowable<Unit> =
        passwordDataSource.changePassword(params).map {
            if (it.code() != 200) throw HttpException(it)
        }

    override fun sendTempPassword(email: String): Flowable<Unit> =
        passwordDataSource.sendTempPassword(email).map {
            if (it.code() != 200) throw HttpException(it)
        }
}