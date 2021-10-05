package com.blueberry.thespace.data.pictureOfDay

import com.blueberry.thespace.data.PictureOfDay
import com.blueberry.thespace.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PictureOfDayRepository @Inject constructor(private val pictureOfDayRemoteRepository: PictureOfDayRemoteRepository) {
    suspend fun getAstronomyPictureForToday(): Flow<Result<PictureOfDay>> = flow {
        emit(Result.Loading())
        try {
            val pod = pictureOfDayRemoteRepository.getAstronomyPictureForToday()
            emit(Result.Success(pod))
//            emit(Result.Success(PictureOfDay(
//                "Anum Amin",
//                "2021-09-30",
//                "Gorgeous spiral galaxy M33 seems to have more than its fair share of glowing hydrogen gas. A prominent member of the local group of galaxies, M33 is also known as the Triangulum Galaxy and lies a mere 3 million light-years away.",
//                "",
//                "image",
//                "The Hydrogen Clouds of M33",
//                "https://apod.nasa.gov/apod/image/2110/NGC4676_HubbleOstling_960.jpg"
//            )))
        } catch (ex: Exception) {
            emit(Result.Failure<PictureOfDay>(ex.message))
        }
    }
}