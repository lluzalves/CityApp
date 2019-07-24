package com.app.daniel.app.citiesapp.base

import android.util.Log
import timber.log.Timber

class MvpViewExceptionImpl : MvpViewException, RuntimeException() {

    override fun onAttachedException(): RuntimeException {
        val error = "Please call Presenter.attachView(MvpView) before" +
                " requesting data to the Presenter"
        val exception = RuntimeException(error)
        Timber.log(Log.ERROR, exception.localizedMessage)
        return exception
    }
}