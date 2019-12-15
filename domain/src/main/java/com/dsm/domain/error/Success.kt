package com.dsm.domain.error

data class Success<T>(val data: T, val isLocal: Boolean = false)