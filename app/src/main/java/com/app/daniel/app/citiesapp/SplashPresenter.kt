package com.app.daniel.app.citiesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.app.daniel.app.citiesapp.base.BasePresenter
import com.app.daniel.app.citiesapp.base.MvpView
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency
import timber.log.Timber

class SplashPresenter : BasePresenter<MvpView>(), Observer<List<WorkInfo>> {

    private val workManager = WorkManager.getInstance(ApplicationDependency.SHARED.getActivity().applicationContext)
    private lateinit var workObserver: LiveData<List<WorkInfo>>

    private val request = OneTimeWorkRequest.Builder(ApplicationDependency.SHARED.retriveFetchWorkClass())
        .addTag(FETCH_WORK)
        .build()


    fun startFetchData() {
        workManager.beginWith(request).enqueue()
        workObserver = workManager.getWorkInfosByTagLiveData(FETCH_WORK)
        workObserver.observeForever(this)
    }

    override fun onChanged(worksInfo: List<WorkInfo>) {
        for (workItem in worksInfo) {
            when (workItem.state) {
                WorkInfo.State.ENQUEUED -> Timber.d("Fetch work enqueued")
                WorkInfo.State.RUNNING -> Timber.d("Fetch work is running")
                WorkInfo.State.SUCCEEDED -> {
                    mvpView?.onCompleted()
                    clearWork()
                }
                WorkInfo.State.FAILED -> {
                    mvpView?.onError(workItem.state.toString())
                    clearWork()

                }
                WorkInfo.State.BLOCKED -> Timber.d("Fetch work is still blocked")
                WorkInfo.State.CANCELLED -> {
                    Timber.d("Fetch work is cancelled")
                    clearWork()
                }
                else -> Timber.d("Fetch work have unknown state")
            }
        }
    }

    private fun clearWork() {
        workManager.pruneWork()
        workObserver.removeObserver(this)
    }

    companion object {
        const val FETCH_WORK = "fetch_work"
    }

}