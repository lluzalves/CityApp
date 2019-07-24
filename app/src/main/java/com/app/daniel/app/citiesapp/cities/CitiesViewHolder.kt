package com.app.daniel.app.citiesapp.cities

import android.view.View
import com.app.daniel.app.citiesapp.base.BaseViewHolder
import com.app.daniel.app.citiesapp.dependency.ApplicationDependency
import com.daniel.domain.dto.City
import kotlinx.android.synthetic.main.cell_city_item.view.*

class CitiesViewHolder constructor(itemView: View) : BaseViewHolder<City>(itemView) {
    private lateinit var city: City
    private val useCase = ApplicationDependency.SHARED.retrieveCityUseCase()
    override fun show(model: City) {
        city = model
        itemView.cityName.text = city.name
        itemView.cityUf.text = city.uf
        itemView.cityCod.text = city.code
    }


}