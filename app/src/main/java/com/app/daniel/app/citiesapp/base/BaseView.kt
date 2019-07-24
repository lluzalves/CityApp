package com.app.daniel.app.citiesapp.base

interface BaseView {

    fun onLoading(loadingMessage : String)

    fun onCompleted()

    fun onError(errorMessage : String)

    fun onError(throwable: Throwable)

}