package com.dsm.data.dataSource.password

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import retrofit2.Response

class PasswordDataSourceImpl(private val api: Api) : PasswordDataSource {
    override fun sendPasswordCode(email: String): Flowable<Response<Unit>> =
        api.sendPasswordCode(email).addSchedulers()

    override fun passwordCodeConfirm(body: Any): Flowable<Response<Unit>> =
        api.passwordCodeConfirm(body).addSchedulers()

    override fun changePassword(newPassword: String): Flowable<Response<Unit>> =
        api.changePassword(newPassword).addSchedulers()

    override fun changePassword(email: String, newPassword: String): Flowable<Response<Unit>> =
        api.changePassword(email, newPassword).addSchedulers()
}