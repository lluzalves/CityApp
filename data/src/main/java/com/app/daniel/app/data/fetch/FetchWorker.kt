package com.app.daniel.app.data.fetch

import android.content.Context
import androidx.concurrent.futures.ResolvableFuture
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.app.daniel.app.data.dependency.DataDependency
import com.app.daniel.app.data.entity.cities.CityEntity
import com.app.daniel.app.data.service.CityService
import com.daniel.common.applyScheduler
import com.google.common.util.concurrent.ListenableFuture
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class FetchWorker constructor(appContext: Context, workerParameters: WorkerParameters) :
    ListenableWorker(appContext, workerParameters) {
    private val compositeDisposable = CompositeDisposable()
    private lateinit var resolvableFuture: ResolvableFuture<Result>

    override fun startWork(): ListenableFuture<Result> {
        resolvableFuture = ResolvableFuture.create()
        compositeDisposable.addAll(DataDependency.SHARED.getApiClient()
            .create(CityService::class.java)
            .getCitiesFromRemote()
            .applyScheduler()
            .subscribeBy(
                onSuccess = { response ->
                    saveInLocalDb(response.cities)
                },
                onError = {
                    Timber.e(it.localizedMessage)
                }
            )
        )
        return resolvableFuture
    }

    private fun saveInLocalDb(cities: List<CityEntity>) {
        compositeDisposable.addAll(DataDependency.SHARED.getCityRepository().upsertCities(cities)
            .applyScheduler()
            .subscribeBy(
                onSuccess = {
                    Timber.d("upsert resolvableFuture %s", it)
                    resolvableFuture.set(Result.success())
                },
                onError = {
                    Timber.e(it.localizedMessage)
                    resolvableFuture.set(Result.failure())
                }
            )
        )
    }
}