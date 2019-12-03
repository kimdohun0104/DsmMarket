package com.dsm.domain.error

sealed class Resource<T> {

    data class Success<T>(val data: T, val isLocal: Boolean = false) : Resource<T>()

    data class Error<T>(val error: ErrorEntity) : Resource<T>()
}