package com.daniel.common

import com.daniel.common.AppSchedulers.mainThreadScheduler
import com.daniel.common.AppSchedulers.networkScheduler
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applyScheduler(): Observable<T> = this.compose { observable ->
    observable
        .subscribeOn(networkScheduler())
        .observeOn(mainThreadScheduler())
}

fun <T> Single<T>.applyScheduler(): Single<T> = this.compose { single ->
    single
        .subscribeOn(networkScheduler())
        .observeOn(mainThreadScheduler())
}

object AppSchedulers{

    fun networkScheduler() : Scheduler {
        return Schedulers.io()
    }

    fun mainThreadScheduler() : Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun computationScheduler() : Scheduler {
        return Schedulers.computation()
    }

}