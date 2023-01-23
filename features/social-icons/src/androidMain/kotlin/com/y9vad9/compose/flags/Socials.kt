package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.y9vad9.pomodoro.client.R

actual object Socials {
    actual val GitHub: Painter
        @Composable get() = painterResource(R.drawable.github)
    actual val Telegram: Painter
        @Composable get() = painterResource(R.drawable.telegram)
}