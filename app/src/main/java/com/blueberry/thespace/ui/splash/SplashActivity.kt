package com.blueberry.thespace.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.blueberry.thespace.MainActivity
import com.blueberry.thespace.R
import com.blueberry.thespace.ui.theme.TheSpaceTheme
import com.blueberry.thespace.ui.theme.optienFamily
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheSpaceTheme {
                splashScreen { launchMainActivity(this) }
            }
        }
    }
}

private const val splashWaitTime: Long = 3000

@Composable
fun splashScreen(onTimeout: () -> Unit) {
    Surface(color = MaterialTheme.colors.primary) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.wrapContentSize(align = Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_planet),
                    contentDescription = "",
                    contentScale = ContentScale.Inside
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontFamily = optienFamily
                )
            }

            // Adds composition consistency. Use the value when LaunchedEffect is first called
            val currentOnTimeout by rememberUpdatedState(onTimeout)

            LaunchedEffect(Unit) {
                delay(splashWaitTime)
                currentOnTimeout()
            }
        }
    }
}

private fun launchMainActivity(context: Context) {
    context.startActivity(createMainActivityIntent(context))
}

fun createMainActivityIntent(context: Context) =
    Intent(context, MainActivity::class.java)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheSpaceTheme {
        splashScreen {}
    }
}