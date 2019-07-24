package com.app.daniel.app.citiesapp.dependency

import androidx.work.ListenableWorker
import com.app.daniel.app.citiesapp.MainActivity
import com.app.daniel.app.citiesapp.application.CitiesApp
import com.app.daniel.app.citiesapp.base.BaseActivity
import com.app.daniel.app.data.dependency.DataDependency
import com.app.daniel.app.data.repository.CityRepository
import com.daniel.domain.dependency.DomainDependency
import com.daniel.domain.usecase.cities.StartCitiesUseCase

class ApplicationDependency {
    private lateinit var mainActivity: BaseActivity
    private lateinit var cityRepository: CityRepository

    fun inject() {
        DataDependency.SHARED.inject(CitiesApp.appInstance)
        cityRepository = DataDependency.SHARED.getCityRepository()
        DomainDependency.SHARED.inject(cityRepository)
    }

    fun inject(mainActivity: BaseActivity) {
        this.mainActivity = mainActivity
    }

    fun retrieveCityUseCase(): StartCitiesUseCase? {
        return DomainDependency.SHARED.getCitiesUseCase()
    }

    fun getActivity(): BaseActivity {
        return mainActivity
    }

    fun retriveFetchWorkClass(): Class<out ListenableWorker> {
        return DataDependency.SHARED.getFetchWorkClass()
    }

    companion object {
        val SHARED: ApplicationDependency = ApplicationDependency()
    }
}