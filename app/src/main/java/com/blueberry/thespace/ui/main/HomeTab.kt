package com.blueberry.thespace

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.ui.main.MainViewModel

@Composable
fun HomeTab(viewModel: MainViewModel, onClick: () -> Unit) {

    val uiState =
        viewModel.exploreHomeData.observeAsState(initial = Result.Loading()) //can use produceState also

    Crossfade(targetState = uiState) {
        when (it.value) {
            is Result.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is Result.Success -> {
                homeExploreContainer((it.value as Result.Success<List<HomeExplore>>).data, onClick)
            }
            else -> {
                showEmptyLabel()
            }
        }
    }
}

@Composable
private fun showEmptyLabel() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.home_content_error),
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun homeExploreContainer(list: List<HomeExplore>, onClick: () -> Unit) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                LazyColumn {
                    items(list.size) { HomeExploreItem(item = list[it], modifier = modifier, onClick = onClick) }
                }
            }
        }
    }
}

@Composable
private fun HomeExploreItem(item: HomeExplore, modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.padding(top = 5.dp, bottom = 5.dp)) {
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_planet),
                    contentDescription = null
                )
                Text(text = item.title)
            }
        }
    }
}

@Preview
@Composable
fun previewHomeTab() {
    HomeTab(MainViewModel()) {

    }
}