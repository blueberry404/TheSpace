package com.blueberry.thespace.data.home

import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.NavigationItem

class HomeLocalRepository {

    fun getTabsList() = listOf(NavigationItem.Home, NavigationItem.PictureOfDay, NavigationItem.Events)

    fun getHomeScreenContent() = listOf(
        HomeExplore("Solar System and Exo Planets", "https://images.yourstory.com/cs/wordpress/2017/12/Exoplanets.png?fm=auto&ar=2:1&mode=crop&crop=faces&w=500"),
        HomeExplore("Constellations", "https://www.wallpaperflare.com/static/84/454/534/galaxy-stellar-stars-vibrant-wallpaper-preview.jpg"),
        HomeExplore("Perseverance Rover", "https://i.insider.com/5e5fe021fee23d42bd018c92?width=500")
    )
}