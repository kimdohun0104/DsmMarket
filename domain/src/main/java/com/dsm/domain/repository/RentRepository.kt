package com.dsm.domain.repository

import com.dsm.domain.entity.Product
import io.reactivex.Completable
import io.reactivex.Flowable

interface RentRepository {

    fun getRemoteRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>>

    fun getLocalRentList(page: Int, pageSize: Int): List<Product>

    fun addLocalRentList(list: List<Product>): Completable

    fun getRemoteInterestRent(): Flowable<List<Product>>

    fun getLocalInterestRent(): List<Product>

    fun addLocalInterestRent(interestRent: List<Product>): Completable
}