package com.app.daniel.app.citiesapp.cities

import com.app.daniel.app.citiesapp.base.MvpView
import com.daniel.domain.dto.City

interface CitiesMvp : MvpView {
    fun getCities(cities : List<City>)
}