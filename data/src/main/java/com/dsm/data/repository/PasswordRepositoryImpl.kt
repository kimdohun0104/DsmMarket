package com.dsm.data.repository

import com.dsm.data.dataSource.password.PasswordDataSource
import com.dsm.domain.repository.PasswordRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class PasswordRepositoryImpl(private val passwordDataSource: PasswordDataSource) : PasswordRepository {

    override fun confirmPassword(password: String): Flowable<Pair<String, Int>> =
        passwordDataSource.confirmPassword(password).map {
            if (it.code() != 200) throw HttpException(it)
            Pair(it.body()!!.email, it.body()!!.authCode)
        }

    override fun sendPasswordCode(email: String): Flowable<Unit> =
        passwordDataSource.sendPasswordCode(email).map {
            if (it.code() != 200) throw HttpException(it)
        }

    override fun passwordCodeConfirm(body: Any): Flowable<Int> =
        passwordDataSource.passwordCodeConfirm(body).map {
            if (it.code() != 200) throw HttpException(it)
            it.body()!!.authCode
        }

    override fun changePassword(params: Any): Flowable<Unit> =
        passwordDataSource.changePassword(params).map {
            if (it.code() != 200) throw HttpException(it)
        }

}