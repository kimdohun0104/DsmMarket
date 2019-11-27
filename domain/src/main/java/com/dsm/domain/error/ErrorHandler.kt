package com.dsm.domain.error

interface ErrorHandler {

    fun getError(throwable: Throwable) : ErrorEntity
}