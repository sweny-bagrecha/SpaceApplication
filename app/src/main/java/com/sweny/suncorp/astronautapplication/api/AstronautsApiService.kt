package com.sweny.suncorp.astronautapplication.api

import com.sweny.suncorp.astronautapplication.dto.AstronautResponse
import retrofit2.Response
import retrofit2.http.GET

interface AstronautsApiService {

    @GET(ApiConstants.ASTRONAUT_LIST)
    suspend fun getAstronautsList(): Response<AstronautResponse>

}