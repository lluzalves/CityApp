package com.app.daniel.app.data.dependency

import android.app.Application
import androidx.work.ListenableWorker
import com.app.daniel.app.data.common.CitiesConstants
import com.app.daniel.app.data.fetch.FetchWorker
import com.app.daniel.app.data.network.NetworkFactory
import com.app.daniel.app.data.persistence.AppDatabase
import com.app.daniel.app.data.repository.CityRepository
import retrofit2.Retrofit

class DataDependency{

    private lateinit var application : Application

    fun getApiClient(): Retrofit {
        return NetworkFactory().httpClient(CitiesConstants.CitiesApi.BASE_URL).newBuilder().build()
    }

    fun getCityRepository(): CityRepository {
        return CityRepository()
    }


    fun inject(application: Application){
        this.application = application
    }

    fun getAppDatabase(): AppDatabase? {
        return  AppDatabase.getDatabase(application.applicationContext)
    }

    fun getFetchWorkClass(): Class<out ListenableWorker> {
        return FetchWorker::class.java
    }

    companion object{
        val SHARED: DataDependency = DataDependency()
    }
}