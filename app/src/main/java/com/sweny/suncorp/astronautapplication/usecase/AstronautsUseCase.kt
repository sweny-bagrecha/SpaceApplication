package com.sweny.suncorp.astronautapplication.usecase

import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.repository.ISpaceRepository
import com.sweny.suncorp.astronautapplication.utils.orDefault
import com.wtpapp.data.dto.AstronautDto
import javax.inject.Inject


interface IAstronautsUseCase {
    suspend fun getAstronauts(): List<AstronautData>
    suspend fun getAstronautDetails(id: String): AstronautData?
}

/**
 * Responsible for all astronauts related business logic
 */
class AstronautsUseCaseImpl @Inject constructor(private val repo: ISpaceRepository) : IAstronautsUseCase {

    override suspend fun getAstronauts(): List<AstronautData> {
        return repo.getAstronauts().map { astronautDto ->
            AstronautData(
                astronautDto.id.orDefault(),
                astronautDto.name.orDefault(),
                astronautDto.nationality.orDefault(),
                astronautDto.profile_image_thumnail.orDefault(),
                astronautDto.bio.orDefault(),
                astronautDto.date_of_birth.orDefault(),
            )
        }
    }

    override suspend fun getAstronautDetails(id: String): AstronautData? {
        val dto =
            repo.getAstronaut(id)
        return dto?.let {
            ModelMapper.astronautDetails(it)
        }
    }

    object ModelMapper {
        fun astronautDetails(astronautdetails: AstronautDto?) =
            AstronautData(
                "",
                astronautdetails?.name.orEmpty(),
                astronautdetails?.nationality.orEmpty(),
                astronautdetails?.profile_image_thumnail.orEmpty(),
                astronautdetails?.bio.orEmpty(),
                astronautdetails?.date_of_birth.orEmpty()
            )
    }
}