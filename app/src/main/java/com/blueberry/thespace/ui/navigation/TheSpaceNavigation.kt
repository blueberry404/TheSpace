package com.blueberry.thespace.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blueberry.thespace.HomeTab
import com.blueberry.thespace.PictureOfDayTab

@Composable
fun TheSpaceNavigation(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = NavKeys.NAV_HOME,
        modifier = modifier
    ) {
        composable(NavKeys.NAV_HOME) { HomeTab(hiltViewModel()) {} }
        composable(NavKeys.NAV_POD) { PictureOfDayTab(hiltViewModel()) }
    }
}

object NavKeys {
    const val NAV_HOME = "HOME"
    const val NAV_POD = "PICTURE_OF_DAY"
}