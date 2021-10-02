package com.blueberry.thespace.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.NavigationItem
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.data.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(homeRepository: HomeRepository): ViewModel() {

    private var _tabList = homeRepository.getTabsList().asLiveData()

    val tabsList: LiveData<List<NavigationItem>>
        get() = _tabList

    private var _exploreHomeData = homeRepository.getHomeScreenContent().asLiveData(viewModelScope.coroutineContext)

    val exploreHomeData: LiveData<Result<List<HomeExplore>>>
    get() = _exploreHomeData
}