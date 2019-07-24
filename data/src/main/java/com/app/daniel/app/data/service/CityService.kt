package com.app.daniel.app.data.service

import com.app.daniel.app.data.common.CitiesConstants
import com.app.daniel.app.data.entity.Response
import io.reactivex.Single
import retrofit2.http.GET

interface CityService {

    @GET(CitiesConstants.CitiesApi.BASE_URL+"jsonTest.php")
    fun getCitiesFromRemote(): Single<Response>
}