/*
 * Copyright 2021 Jaya Surya Thotapalli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayasuryat.uigame.feedback

import android.content.Context
import android.media.MediaPlayer
import com.jayasuryat.uigame.R
import com.jayasuryat.util.TrackedLazyCollector

internal class MusicManager(private val context: Context) {

    private val tlc: TrackedLazyCollector<MediaPlayer> by lazy { TrackedLazyCollector() }

    // region : MediaPlayers
    private val sfxPop: MediaPlayer by mediaPlayerOf(R.raw.pop) {
        setVolume(0.05f, 0.05f)
    }
    private val sfxAffirmative: MediaPlayer by mediaPlayerOf(R.raw.affirmative) {
        setVolume(0.05f, 0.05f)
    }
    private val sfxCancel: MediaPlayer by mediaPlayerOf(R.raw.cancel) {
        setVolume(0.02f, 0.02f)
    }
    private val sfxSuccess: MediaPlayer by mediaPlayerOf(R.raw.success) {
        setVolume(0.02f, 0.02f)
    }

    private val sfxFailure: MediaPlayer by mediaPlayerOf(R.raw.failure) {
        setVolume(0.02f, 0.02f)
    }
    // endregion

    // region : Public API
    fun pop() = sfxPop.start()
    fun affirmative() = sfxAffirmative.start()
    fun cancel() = sfxCancel.start()
    fun success() = sfxSuccess.start()
    fun failure() = sfxFailure.start()

    fun dispose() {
        tlc.onEachInitialized { player ->
            player.stop()
            player.release()
        }
    }
    // endregion

    // region : Helper methods

    private val MediaPlayer.isPaused: Boolean
        get() = !isPlaying && currentPosition > 1

    private fun mediaPlayerOf(
        resId: Int,
        configuration: MediaPlayer.() -> Unit = {},
    ): Lazy<MediaPlayer> = tlc.trackedLazy {
        MediaPlayer
            .create(context, resId)
            .apply { configuration() }
    }
    // endregion
}
