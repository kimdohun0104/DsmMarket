package com.dsm.domain.service

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Success
import io.reactivex.Flowable

fun <T> Flowable<T>.toSuccess(errorHandler: ErrorHandler): Flowable<Success<T>> =
    this.map { Success(it) }
        .doOnError { throw errorHandler.getError(it) }

fun <T> Flowable<T>.handleError(errorHandler: ErrorHandler): Flowable<T> =
    this.doOnError { throw errorHandler.getError(it) }