package com.sweny.suncorp.astronautapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sweny.suncorp.astronautapplication.api.ApiConstants
import com.sweny.suncorp.astronautapplication.api.AstronautsApiService
import com.sweny.suncorp.astronautapplication.repository.ISpaceRepository
import com.sweny.suncorp.astronautapplication.usecase.AstronautsUseCaseImpl
import com.sweny.suncorp.astronautapplication.usecase.IAstronautsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppCorporateModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder()
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAstronautsService(retrofit: Retrofit): AstronautsApiService =
        retrofit.create(AstronautsApiService::class.java)

}