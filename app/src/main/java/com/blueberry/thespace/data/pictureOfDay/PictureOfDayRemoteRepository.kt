package com.blueberry.thespace.data.pictureOfDay

import com.blueberry.thespace.data.PictureOfDay
import com.blueberry.thespace.restapi.PictureOfDayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PictureOfDayRemoteRepository @Inject constructor(private val pictureOfDayService: PictureOfDayService) {
    suspend fun getAstronomyPictureForToday(): PictureOfDay =
        withContext(Dispatchers.IO) {
            return@withContext pictureOfDayService.getPictureOfTheDay()
        }
}