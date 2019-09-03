package com.dsm.domain.base

interface Mapper<T, E> {
    fun mapFrom(from: T): E
}