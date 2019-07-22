package com.app.daniel.app.data.entity

import com.app.daniel.app.data.adapter.CityAdapter
import com.app.daniel.app.data.entity.cities.CityEntity
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName(CITIES)
    private val cities: List<CityEntity>
) {

    fun mapToCityList(): List<CityEntity> {
        val cityList = ArrayList<CityEntity>()
        cities.mapTo(destination = cityList) { cityEntity -> CityAdapter.toCity(cityEntity) }
        return cityList.toList()
    }

    companion object {
        const val CITIES = "cities"
    }
}