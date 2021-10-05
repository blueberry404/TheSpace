package com.blueberry.thespace.ui.main

import androidx.lifecycle.*
import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.NavigationItem
import com.blueberry.thespace.data.PictureOfDay
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.data.home.HomeRepository
import com.blueberry.thespace.data.pictureOfDay.PictureOfDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    homeRepository: HomeRepository,
    private val pictureOfDayRepository: PictureOfDayRepository
) : ViewModel() {

    private var _tabList = homeRepository.getTabsList().asLiveData()

    val tabsList: LiveData<List<NavigationItem>>
        get() = _tabList

    private var _exploreHomeData =
        homeRepository.getHomeScreenContent().asLiveData(viewModelScope.coroutineContext)

    val exploreHomeData: LiveData<Result<List<HomeExplore>>>
        get() = _exploreHomeData

    private var _pictureOfDayResult = MutableLiveData<Result<PictureOfDay>>(Result.Loading())

    val pictureOfDayResult: LiveData<Result<PictureOfDay>>
        get() = _pictureOfDayResult

    private fun getPictureOfToday() {
        viewModelScope.launch {
            _pictureOfDayResult = pictureOfDayRepository.getAstronomyPictureForToday()
                .asLiveData(viewModelScope.coroutineContext) as MutableLiveData<Result<PictureOfDay>>
        }
    }

    init {
        getPictureOfToday()
    }
}