package com.blueberry.thespace

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blueberry.thespace.data.HomeExplore
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.ui.main.MainViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun HomeTab(viewModel: MainViewModel = viewModel(), onClick: () -> Unit) {

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
                .fillMaxSize()
                .padding(15.dp)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(list.size) { HomeExploreItem(item = list[it], onClick = onClick) }
            }
        }
    }
}

@Composable
private fun HomeExploreItem(item: HomeExplore, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CoilImage(
                imageModel = item.imageURL,
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.FillBounds,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 200),
                // shows a placeholder ImageBitmap when loading.
                // shows a shimmering effect when loading an image.
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = colorResource(id = R.color.shimmer_highlight),
                    durationMillis = 500,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                modifier = Modifier.height(250.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(colorResource(id = R.color.light_black))
                    .alpha(0.3f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h5,
                    color = colorResource(id = R.color.white),
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
