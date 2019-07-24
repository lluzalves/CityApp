package com.app.daniel.app.data.entity

import com.app.daniel.app.data.adapter.CityAdapter
import com.app.daniel.app.data.entity.cities.CityEntity
import com.daniel.domain.dto.City
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName(CITIES) val cities: List<CityEntity>
) {

    fun mapToCityList(): List<City> {
        val cityList = ArrayList<City>()
        return cities.mapTo(destination = cityList) { cityEntity -> CityAdapter().toCity(cityEntity) }
    }

    companion object {
        const val CITIES = "CIDADES"
    }
}