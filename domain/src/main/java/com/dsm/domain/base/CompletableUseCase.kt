package com.dsm.domain.base

import io.reactivex.Completable

abstract class CompletableUseCase<T> {
    abstract fun create(data: T) : Completable
}