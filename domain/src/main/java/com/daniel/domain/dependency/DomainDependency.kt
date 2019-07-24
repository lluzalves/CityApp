package com.daniel.domain.dependency

import com.daniel.domain.repository.ICityRepository
import com.daniel.domain.usecase.cities.StartCitiesUseCase

class DomainDependency {
    private lateinit var citiesUseCase: StartCitiesUseCase

    fun inject(repository: ICityRepository) {
        this.citiesUseCase = StartCitiesUseCase(repository)
    }

    fun getCitiesUseCase(): StartCitiesUseCase? {
        return when {
            ::citiesUseCase.isInitialized -> citiesUseCase
            else -> null
        }
    }

    companion object {
        val SHARED: DomainDependency = DomainDependency()
    }

}