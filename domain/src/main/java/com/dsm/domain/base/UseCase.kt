package com.dsm.domain.base

import io.reactivex.Flowable

abstract class UseCase<T, E> {
    abstract fun create(data: T): Flowable<E>
}