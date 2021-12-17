package com.jayasuryat.uigame.composable.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.jayasuryat.uigame.feedback.MusicManager
import com.jayasuryat.uigame.feedback.VibrationManager
import com.jayasuryat.uigame.logic.GameState
import com.jayasuryat.util.LogCompositions

@Composable
internal fun GameFeedback(
    gameState: State<GameState>,
) {

    LogCompositions(name = "GameFeedback")

    val context = LocalContext.current
    val vibrationManager = remember { VibrationManager(context) }
    val musicManager = remember { MusicManager(context) }

    when (gameState.value) {

        is GameState.Idle -> Unit

        is GameState.GameStarted -> Unit

        is GameState.GameEnded.GameCompleted -> {
            musicManager.success()
            vibrationManager.shortVibrationNow()
        }

        is GameState.GameEnded.GameOver -> {
            musicManager.failure()
            vibrationManager.mediumVibrationNow()
        }
    }
}