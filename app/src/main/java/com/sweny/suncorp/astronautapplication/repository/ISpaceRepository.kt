package com.sweny.suncorp.astronautapplication.repository

import com.google.gson.Gson
import com.sweny.suncorp.astronautapplication.api.AstronautsApiService
import com.wtpapp.data.dto.AstronautDto
import com.wtpapp.data.dto.AstronautResponseException
import com.wtpapp.data.dto.BaseAstronautResponse
import retrofit2.Response
import javax.inject.Inject

interface ISpaceRepository {
    suspend fun getAstronauts(): ArrayList<AstronautDto>
    suspend fun getAstronaut(astronautId: String): AstronautDto?
}

/**
 * Trips repository with local cache
 *
 * @property apiService
 * @property data
 *
 */
class SpaceRepositoryImpl @Inject constructor(
    val apiService: AstronautsApiService) : ISpaceRepository {

    private var astronautsDtoCache = ArrayList<AstronautDto>()

    /**
     * Process api call - helper method to process Api call and extract response body
     *
     * Api errors ae thrown to be processed by view model
     *
     * @param T
     * @param apiCall
     * @receiver
     * @return Response Body
     */
    suspend fun <T> processApiCall(apiCall: suspend () -> Response<T>): T {
        val response: Response<T>
        try {
            response = apiCall()
        } catch (e: Exception) {
            throw AstronautResponseException(-1, e.message ?: e.toString())
        }
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
        }
        response.errorBody()?.let {
            val ex = parseErrorBody(it.charStream().readText())
            throw  ex
        }
        throw AstronautResponseException(response.code(), response.message())
    }

    /**
     * Parse error body - helper method to convert error response into TripResponseException
     *
     * @param errorBody
     */
    private fun parseErrorBody(errorBody: String) =
        try {
            val rsp = Gson().fromJson(errorBody, BaseAstronautResponse::class.java)
            AstronautResponseException(rsp.status, rsp.message)
        } catch (e: Exception) {
            AstronautResponseException(-1, e.message ?: e.toString())
        }

    override suspend fun getAstronauts(): ArrayList<AstronautDto> =
        processApiCall {
            apiService.getAstronautsList()
        }.result.also {
            //setup local cache
            astronautsDtoCache = it
        }

    override suspend fun getAstronaut(astronautId: String): AstronautDto? =
        astronautsDtoCache.firstOrNull { it.id == astronautId } ?: run {
            getAstronauts().firstOrNull { it.id == astronautId }
    }

}

