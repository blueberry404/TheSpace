package com.blueberry.thespace

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blueberry.thespace.data.NavigationItem
import com.blueberry.thespace.ui.main.MainViewModel
import com.blueberry.thespace.ui.navigation.NavKeys
import com.blueberry.thespace.ui.navigation.TheSpaceNavigation
import com.blueberry.thespace.ui.theme.TheSpaceTheme
import com.blueberry.thespace.ui.theme.optienFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheSpaceTheme {
                mainContent()
            }
        }
    }
}

enum class MainTabs { HOME, PICTURE }

@Composable
fun mainContent(mainViewModel: MainViewModel = viewModel()) {
    var tabList = mainViewModel.tabsList.observeAsState(initial = listOf())
    val navController = rememberNavController()
    Surface {
        Scaffold(
            bottomBar = {
                bottomTabContent(tabList.value, navController)
            },
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.h3,
                        fontFamily = optienFamily
                    )
                }
            }) { innerPadding ->
            TheSpaceNavigation(
                navHostController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun bottomTabContent(
    list: List<NavigationItem>,
    navHostController: NavHostController
) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.black)
    ) {
//        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navHostController.currentDestination
        var tabSelected by remember { mutableStateOf(NavKeys.NAV_HOME) }
        list.forEach { item ->
            BottomNavigationItem(
                selected = isTabSelected(currentDestination, tabSelected),
                icon = {
                    Image(
                        painterResource(
                            id = if (isTabSelected(
                                    currentDestination,
                                    item.route
                                )
                            ) item.selectedIcon else item.unselectedIcon
                        ),
                        null
                    )
                },
                onClick = {
                    tabSelected = item.route
                    navHostController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun isTabSelected(
    currentDestination: NavDestination?,
    route: String
) = currentDestination?.hierarchy?.any { it.route == route } == true

