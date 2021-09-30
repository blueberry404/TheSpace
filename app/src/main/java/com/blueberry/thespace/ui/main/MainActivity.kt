package com.blueberry.thespace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blueberry.thespace.data.NavigationItem
import com.blueberry.thespace.ui.main.MainViewModel
import com.blueberry.thespace.ui.theme.TheSpaceTheme
import com.blueberry.thespace.ui.theme.optienFamily

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheSpaceTheme {
                val list by mainViewModel.tabsList.observeAsState()
                mainContent(list ?: listOf(), mainViewModel)
            }
        }
    }
}

enum class MainTabs { HOME, PICTURE, EVENTS }

@Composable
fun mainContent(list: List<NavigationItem>, mainViewModel: MainViewModel) {
    var selectedTab by remember { mutableStateOf(MainTabs.HOME) }
    Surface {
        Scaffold(bottomBar = {
            bottomTabContent(list, selectedTab) { selectedTab = it }
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
                        fontWeight = FontWeight.Bold,
                        fontFamily = optienFamily
                    )
                }
            }) { innerPadding ->
            Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())) {
                SelectedTabContent(selectedTab, mainViewModel)
            }
        }
    }
}

@Composable
fun bottomTabContent(
    list: List<NavigationItem>,
    selectedTab: MainTabs,
    onTabSelected: (MainTabs) -> Unit
) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.black)
    ) {
        list.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = index == selectedTab.ordinal,
                icon = {
                    Image(
                        painterResource(id = if (index == selectedTab.ordinal) item.selectedIcon else item.unselectedIcon),
                        null
                    )
                },
                onClick = { onTabSelected(MainTabs.values()[index]) })
        }
    }
}

@Composable
private fun SelectedTabContent(selectedTab: MainTabs, viewModel: MainViewModel) {
    when (selectedTab) {
        MainTabs.HOME -> HomeTab(viewModel) {

        }
        MainTabs.PICTURE -> PictureOfDayTab()
        MainTabs.EVENTS -> EventsTab()
    }
}

@Preview(showBackground = true)
@Composable
fun mainContentPreview() {
    var list = listOf(NavigationItem.Home, NavigationItem.PictureOfDay, NavigationItem.Events)
    mainContent(list, MainViewModel())
}


