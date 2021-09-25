package com.blueberry.thespace

import androidx.annotation.DrawableRes

sealed class NavigationItem(var route: String, @DrawableRes var selectedIcon: Int, @DrawableRes var unselectedIcon: Int, var title: String) {
    object Home: NavigationItem("home", R.drawable.ic_home_filled, R.drawable.ic_home_outline, "Home")
    object PictureOfDay: NavigationItem("picture", R.drawable.ic_pic_filled, R.drawable.ic_pic_outline,"Picture")
    object Events: NavigationItem("events", R.drawable.ic_event_filled, R.drawable.ic_event_outline, "Events")
}
