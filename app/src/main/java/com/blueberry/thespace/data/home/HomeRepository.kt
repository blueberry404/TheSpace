package com.blueberry.thespace.data.home

import com.blueberry.thespace.data.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(val homeLocalRepository: HomeLocalRepository) {

    fun getTabsList() = flow {
        emit(homeLocalRepository.getTabsList())
    }

    fun getHomeScreenContent() = flow {
        emit(Result.Loading())
        delay(1000)
        emit(Result.Success(homeLocalRepository.getHomeScreenContent()))
    }
}