package com.blueberry.thespace.data

import androidx.annotation.DrawableRes
import com.blueberry.thespace.R
import com.blueberry.thespace.ui.navigation.NavKeys

sealed class NavigationItem(
    @DrawableRes var selectedIcon: Int,
    @DrawableRes var unselectedIcon: Int,
    var title: String,
    var route: String
) {
    object Home : NavigationItem(
        R.drawable.ic_home_filled,
        R.drawable.ic_home_outline,
        "Home",
        NavKeys.NAV_HOME
    )

    object PictureOfDay : NavigationItem(
        R.drawable.ic_pic_filled,
        R.drawable.ic_pic_outline,
        "Picture",
        NavKeys.NAV_POD
    )
}
