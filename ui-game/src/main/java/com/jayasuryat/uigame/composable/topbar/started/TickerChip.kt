package com.jayasuryat.uigame.composable.topbar.started

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jayasuryat.uigame.composable.topbar.TextChip
import com.jayasuryat.uigame.composable.topbar.formatTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
internal fun TickerChip(
    modifier: Modifier = Modifier,
    startTime: Long,
) {

    val elapsedDuration = remember { mutableStateOf(0L) }

    LaunchedEffect(key1 = startTime) {
        while (this.isActive) {
            delay(1000)
            elapsedDuration.value = System.currentTimeMillis() - startTime
        }
    }

    TextChip(
        modifier = modifier,
        text = formatTime(elapsedDuration.value),
        contentColor = MaterialTheme.colors.background,
        textColor = MaterialTheme.colors.onBackground,
        strokeColor = MaterialTheme.colors.onBackground,
    )
}

@Preview
@Composable
private fun Preview() {

    TickerChip(
        startTime = System.currentTimeMillis()
    )
}