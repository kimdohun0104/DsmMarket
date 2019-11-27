package com.dsm.domain.error

import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity =
        when (throwable) {
            is IOException -> ErrorEntity.Internal(throwable)
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> ErrorEntity.Internal(throwable)

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.Forbidden(throwable)

                    else -> ErrorEntity.Unknown(throwable)
                }
            }
            else -> ErrorEntity.Unknown(throwable)
        }

}