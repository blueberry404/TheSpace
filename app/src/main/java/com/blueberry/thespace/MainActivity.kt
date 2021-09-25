package com.blueberry.thespace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blueberry.thespace.ui.theme.TheSpaceTheme
import com.blueberry.thespace.ui.ui.theme.optienFamily
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var list = listOf(NavigationItem.Home, NavigationItem.PictureOfDay, NavigationItem.Events)

        setContent {
            TheSpaceTheme {
                mainContent(list)
            }
        }
    }
}

enum class MainTabs { HOME, PICTURE, EVENTS }

@Composable
fun mainContent(list: List<NavigationItem>) {
    var selectedTab by remember { mutableStateOf(MainTabs.HOME) }
    Surface(color = MaterialTheme.colors.primary) {
        Scaffold(bottomBar = {
            bottomTabContent(list, selectedTab) { selectedTab = it }
        },
        topBar = {
            Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    fontFamily = optienFamily
                )
            }
        }) {
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

@Preview(showBackground = true)
@Composable
fun mainContentPreview() {
    var list = listOf(NavigationItem.Home, NavigationItem.PictureOfDay, NavigationItem.Events)
    mainContent(list)
}


