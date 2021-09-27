package com.blueberry.thespace.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.NavigationItem
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.data.home.HomeLocalRepository
import com.blueberry.thespace.data.home.HomeRepository

class MainViewModel: ViewModel() {
    private val homeRepository = HomeRepository(HomeLocalRepository())

    private var _tabList = homeRepository.getTabsList().asLiveData()

    val tabsList: LiveData<List<NavigationItem>>
        get() = _tabList

    private var _exploreHomeData = homeRepository.getHomeScreenContent().asLiveData(viewModelScope.coroutineContext)

    val exploreHomeData: LiveData<Result<List<HomeExplore>>>
    get() = _exploreHomeData
}