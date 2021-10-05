package com.blueberry.thespace.di

import com.blueberry.thespace.restapi.PictureOfDayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getPictureOfTheService() = PictureOfDayService.create()
}