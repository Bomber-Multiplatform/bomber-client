package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

actual object Socials {
    actual val GitHub: Painter
        @Composable get() = painterResource("github.svg")
    actual val Telegram: Painter
        @Composable get() = painterResource("telegram.svg")
}