package com.app.daniel.app.citiesapp.base

interface MvpPresenter<V : MvpView>{
    fun attachView(mvpView : V)
    fun onDetach()
    fun checkConnectivity()
    fun onConnectivity(isConnected : Boolean) : Boolean
}