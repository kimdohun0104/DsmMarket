package com.dsm.domain

import com.dsm.domain.error.ErrorHandler
import com.dsm.domain.error.Resource
import io.reactivex.Flowable

fun <T> Flowable<T>.toResource(errorHandler: ErrorHandler) =
    this.map<Resource<T>> { Resource.Success(it) }
        .onErrorReturn { Resource.Error(errorHandler.getError(it)) }