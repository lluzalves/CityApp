package com.app.daniel.app.citiesapp.cities

import com.app.daniel.app.citiesapp.base.BasePresenter
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency

class CitiesPresenter : BasePresenter<CitiesMvp>() {

    private val useCase = ApplicationDependency.SHARED.retrieveCityUseCase()

    fun retrieveFetchedCities() {
        useCase?.execute(
            onSuccess = { cities ->
                mvpView?.getCities(cities)
            },
            onError = { throwable ->
                mvpView?.onError(throwable)
            },
            onFinished = {
                mvpView?.onCompleted()
            }
        )
    }
}