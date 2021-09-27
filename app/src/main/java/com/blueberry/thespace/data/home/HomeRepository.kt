package com.blueberry.thespace.data.home

import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class HomeRepository constructor(private val homeLocalRepository: HomeLocalRepository) {

    fun getTabsList() = flow {
        emit(homeLocalRepository.getTabsList())
    }

    fun getHomeScreenContent() = flow {
        emit(Result.Loading())
        delay(3000)
        emit(Result.Success(homeLocalRepository.getHomeScreenContent()))
    }
}