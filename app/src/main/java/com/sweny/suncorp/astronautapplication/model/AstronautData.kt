package com.sweny.suncorp.astronautapplication.model

data class AstronautData(
    val id: String,
    val name: String,
    val nationality: String,
    val profile_image: String,
    val profile_image_thumbnail: String,
    val bio: String,
    val date_of_birth: String
)
