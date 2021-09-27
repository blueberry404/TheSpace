package com.blueberry.thespace

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EventsTab() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Text(text = "Events tab")
    }
}