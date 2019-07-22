package com.app.daniel.app.data

import com.app.daniel.app.data.common.CitiesConstants
import retrofit2.http.GET

interface Cities {

    @GET(CitiesConstants.CitiesApi.BASE_URL)
    fun getCities() : Single<Cidades>
}