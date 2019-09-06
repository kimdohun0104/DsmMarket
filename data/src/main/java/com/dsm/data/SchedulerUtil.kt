package com.dsm.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.addSchedulers(): Flowable<T> =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.addSchedulers(): Completable =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())