package com.blueberry.thespace.restapi

import com.blueberry.thespace.BuildConfig
import com.blueberry.thespace.data.PictureOfDay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfDayService {

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY): PictureOfDay

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"

        fun create(): PictureOfDayService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PictureOfDayService::class.java)
        }
    }
}