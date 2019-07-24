package com.app.daniel.app.citiesapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onLoading(loadingMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted() {

    }

    override fun onError(errorMessage: String) {
        view?.let { Snackbar.make(it, errorMessage, Snackbar.LENGTH_LONG).show() }
    }

    override fun onError(throwable: Throwable) {
        view?.let { Snackbar.make(it, throwable.localizedMessage, Snackbar.LENGTH_LONG).show() }
    }
}