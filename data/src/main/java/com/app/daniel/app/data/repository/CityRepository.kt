package com.app.daniel.app.data.repository

import com.app.daniel.app.data.adapter.CityAdapter
import com.app.daniel.app.data.dependency.DataDependency
import com.app.daniel.app.data.entity.cities.CityEntity
import com.daniel.domain.dto.City
import com.daniel.domain.repository.ICityRepository
import io.reactivex.Single

class CityRepository : ICityRepository {
    private val database = DataDependency.SHARED.getAppDatabase()

    override fun retrieveList(): Single<List<City>> {
        val result = database?.cityDao?.getCities()
        return result?.map { cities ->
            cities.map { city ->
                CityAdapter().toCity(
                    cityEntity = city
                )
            }
        } as Single<List<City>>
    }

    override fun upsertCity(city: City): Single<Long> {
        return database?.cityDao?.upsertCity(CityAdapter().toCityEntity(city)) ?: Single.just((-1).toLong())
    }

    fun upsertCities(cities: List<CityEntity>): Single<Array<Long>> {
        return database?.cityDao?.upsertCities(cities)!!
    }

}