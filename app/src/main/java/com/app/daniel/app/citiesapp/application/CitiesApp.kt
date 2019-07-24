package com.app.daniel.app.citiesapp.application

import android.app.Application

class CitiesApp : Application() {

    override fun onCreate() {
        appInstance = this
        super.onCreate()
    }

    companion object {
        lateinit var appInstance: CitiesApp
    }
}