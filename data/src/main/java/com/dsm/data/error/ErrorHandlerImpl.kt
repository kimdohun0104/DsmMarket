package com.dsm.data.error

import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.GoneException
import com.dsm.data.error.exception.InternalException
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): Exception =
        when (throwable) {
            is IOException -> InternalException(throwable)
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> InternalException(throwable)

                    HttpURLConnection.HTTP_FORBIDDEN -> ForbiddenException(throwable)

                    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException(throwable)

                    HttpURLConnection.HTTP_GONE -> GoneException(throwable)

                    else -> InternalException(throwable)
                }
            }
            else -> InternalException(throwable)
        }

}