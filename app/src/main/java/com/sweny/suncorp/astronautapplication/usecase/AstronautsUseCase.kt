package com.sweny.suncorp.astronautapplication.usecase

import com.sweny.suncorp.astronautapplication.dto.AstronautDto
import com.sweny.suncorp.astronautapplication.model.AstronautData
import com.sweny.suncorp.astronautapplication.repository.ISpaceRepository
import com.sweny.suncorp.astronautapplication.utils.orDefault
import javax.inject.Inject


interface IAstronautsUseCase {
    suspend fun getAstronauts(): List<AstronautData>
    suspend fun getAstronautDetails(id: String): AstronautData
}

/**
 * Responsible for all astronauts related business logic
 */
class AstronautsUseCaseImpl @Inject constructor(private val repo: ISpaceRepository) : IAstronautsUseCase {

    /**
     * Get list of astronauts details
     *
     * @return receive list of astronauts details
     */
    override suspend fun getAstronauts(): List<AstronautData> {
        return repo.getAstronauts().map { astronautDto ->
            AstronautData(
                astronautDto.id.orDefault(),
                astronautDto.name.orDefault(),
                astronautDto.nationality.orDefault(),
                astronautDto.profile_image.orDefault(),
                astronautDto.profile_image_thumbnail.orDefault(),
                astronautDto.bio.orDefault(),
                astronautDto.date_of_birth.orDefault(),
            )
        }
    }

    /**
     * Get data of selected astronaut
     *
     * @param id : astronaut id
     * @return receive data of selected astronaut
     */
    override suspend fun getAstronautDetails(id: String): AstronautData {
        val dto =
            repo.getAstronaut(id)
        return dto.let {
            ModelMapper.astronautDetails(dto)
        }
    }

    object ModelMapper {
        fun astronautDetails(astronautdetails: AstronautDto?) =
            AstronautData(
                "",
                astronautdetails?.name.orEmpty(),
                astronautdetails?.nationality.orEmpty(),
                astronautdetails?.profile_image.orDefault(),
                astronautdetails?.profile_image_thumbnail.orEmpty(),
                astronautdetails?.bio.orEmpty(),
                astronautdetails?.date_of_birth.orEmpty()
            )
    }
}