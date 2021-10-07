package com.sweny.suncorp.astronautapplication.dto

import java.lang.Exception

open class BaseAstronautResponse {
    var success = true
    var status = 0
    val message = ""
}

data class AstronautResponse(
    val results: ArrayList<AstronautDto>
) : BaseAstronautResponse()

data class AstronautDto(
    val id: String? = null,
    val name: String? = null,
    val nationality: String? = null,
    val profile_image: String? = null,
    val profile_image_thumbnail: String? = null,
    val bio: String? = null,
    val date_of_birth: String? = null
)

class AstronautResponseException(val status: Int, val msg: String) :
    Exception("status: $status msg: $msg")
