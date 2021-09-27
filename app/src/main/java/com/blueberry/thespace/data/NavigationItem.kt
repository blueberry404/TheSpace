package com.blueberry.thespace.data

import androidx.annotation.DrawableRes
import com.blueberry.thespace.R

sealed class NavigationItem(@DrawableRes var selectedIcon: Int, @DrawableRes var unselectedIcon: Int, var title: String) {
    object Home: NavigationItem(R.drawable.ic_home_filled, R.drawable.ic_home_outline, "Home")
    object PictureOfDay: NavigationItem(R.drawable.ic_pic_filled, R.drawable.ic_pic_outline,"Picture")
    object Events: NavigationItem(R.drawable.ic_event_filled, R.drawable.ic_event_outline, "Events")
}
