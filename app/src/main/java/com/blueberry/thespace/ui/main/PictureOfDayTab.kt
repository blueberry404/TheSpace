package com.blueberry.thespace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blueberry.thespace.data.PictureOfDay
import com.blueberry.thespace.data.Result
import com.blueberry.thespace.ui.main.MainViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PictureOfDayTab(viewModel: MainViewModel? = null) {
    val scrollState = rememberScrollState()
    val state = viewModel!!.pictureOfDayResult.observeAsState(initial = Result.Loading())
    val result = state.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if(result is Result.Success) Arrangement.Top else Arrangement.Center
    )
    {
        when (result) {
            is Result.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is Result.Failure -> {
                Box(
                    Modifier
                        .fillMaxSize().align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = result.message ?: stringResource(id = R.string.home_content_error),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else -> {
                val data = (result as Result.Success<PictureOfDay>).data
                Text(
                    text = stringResource(id = R.string.picture_of_day_title).uppercase(),
                    style = MaterialTheme.typography.caption,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = data.date.toVerboseDateFormat().uppercase(),
                    style = MaterialTheme.typography.caption,
                    letterSpacing = 4.sp,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = data.title, style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight(500)
                )
                Spacer(modifier = Modifier.height(20.dp))
                if(data.media_type == "image") {
                    CoilImage(
                        imageModel = data.url,
                        contentScale = ContentScale.FillBounds,
                        circularReveal = CircularReveal(duration = 200),
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = colorResource(id = R.color.shimmer_highlight),
                            durationMillis = 500,
                            dropOff = 0.65f,
                            tilt = 20f
                        ),
                        modifier = Modifier.height(250.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Text(
                    text = data.explanation,
                    style = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Justify)
                )
                Spacer(modifier = Modifier.height(10.dp))
                if(data.copyright.isNotBlank()) {
                    Text(
                        text = "© ${data.copyright}",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight(700)
                    )
                }
            }
        }
    }
}
