package com.app.daniel.app.citiesapp.base.work

import android.content.Context
import android.net.ConnectivityManager
import androidx.concurrent.futures.ResolvableFuture
import androidx.lifecycle.LiveData
import androidx.work.ListenableWorker
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture


class NetworkStateIWorkState constructor(private val appContext: Context, params: WorkerParameters) :
    ListenableWorker(appContext, params) {

    var resolvableFuture: ResolvableFuture<Result> = ResolvableFuture.create()

    override fun startWork(): ListenableFuture<Result> {
        val connectivityManager =
            appContext.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        when {
            activeNetwork != null && activeNetwork.isConnected -> {
                resolvableFuture.set(Result.success())
            }
            else -> {
                resolvableFuture.set(Result.failure())
            }
        }
        return resolvableFuture
    }

    companion object {
        const val TAG = "network"
        const val NAME = "network_worker"
    }

}