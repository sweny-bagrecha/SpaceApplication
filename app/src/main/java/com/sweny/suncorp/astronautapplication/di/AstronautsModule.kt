package com.sweny.suncorp.astronautapplication.di

import com.sweny.suncorp.astronautapplication.repository.ISpaceRepository
import com.sweny.suncorp.astronautapplication.repository.SpaceRepositoryImpl
import com.sweny.suncorp.astronautapplication.usecase.AstronautsUseCaseImpl
import com.sweny.suncorp.astronautapplication.usecase.IAstronautsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AstronautsModule {

    @Singleton
    @Binds
    abstract fun bindAstronautsRepo(
        spaceRepo: SpaceRepositoryImpl
    ) : ISpaceRepository

    @Binds
    abstract fun bindAstronautsUseCase(
        austronautsUseCase: AstronautsUseCaseImpl
    ): IAstronautsUseCase
}