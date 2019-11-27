package com.dsm.data.dataSource.password

import com.dsm.data.addSchedulers
import com.dsm.data.remote.Api
import io.reactivex.Flowable
import retrofit2.Response

class PasswordDataSourceImpl(private val api: Api) : PasswordDataSource {
    override fun confirmPassword(password: String): Flowable<Response<Unit>> =
        api.confirmPassword(password).addSchedulers()

    override fun changePassword(password: String): Flowable<Response<Unit>> =
        api.changePassword(password).addSchedulers()

}