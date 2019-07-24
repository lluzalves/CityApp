package com.app.daniel.app.data.adapter

import com.app.daniel.app.data.entity.cities.CityEntity
import com.daniel.domain.dto.City

class CityAdapter {
    fun toCity(cityEntity: CityEntity) = City(
        code = cityEntity.code,
        name = cityEntity.name,
        uf = cityEntity.uf
    )

    fun toCityEntity(city: City) = CityEntity(
        code = city.code,
        name = city.name,
        uf = city.uf
    )
}