package com.daniel.domain.usecase.cities

import com.daniel.common.applyScheduler
import com.daniel.domain.dto.City
import com.daniel.domain.repository.ICityRepository
import com.daniel.domain.usecase.base.UseCase
import io.reactivex.Single

class StartCitiesUseCase constructor(private val repository: ICityRepository) : UseCase<List<City>>() {

    override fun buildUseCase(): Single<List<City>> {
        return repository.retrieveList().applyScheduler()
    }

    fun buildInsertCityUseCase(city: City): Single<Long> {
        return repository.upsertCity(city)
    }

    fun buildRetrieveAllCities(): Single<List<City>> {
        return repository.retrieveList().applyScheduler()

    }
}