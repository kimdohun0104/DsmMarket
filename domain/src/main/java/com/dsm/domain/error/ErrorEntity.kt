package com.dsm.domain.error

sealed class ErrorEntity {

    abstract val originalException: Throwable

    data class Internal(override val originalException: Throwable) : ErrorEntity()

    data class Forbidden(override val originalException: Throwable) : ErrorEntity()

    data class Unauthorized(override val originalException: Throwable) : ErrorEntity()

    data class Gone(override val originalException: Throwable) : ErrorEntity()

    data class Unknown(override val originalException: Throwable) : ErrorEntity()
}