package com.daniel.domain.repository

import com.daniel.domain.dto.City
import io.reactivex.Single

interface ICityRepository : IRepository<City> {
    fun upsertCity(city: City): Single<Long>
}