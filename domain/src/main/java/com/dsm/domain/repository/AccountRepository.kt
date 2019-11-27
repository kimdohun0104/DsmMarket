package com.dsm.domain.repository

import io.reactivex.Flowable

interface AccountRepository {



    fun getUserNick(): Flowable<String?>

    fun changeUserNick(newNick: String): Flowable<Unit>
}