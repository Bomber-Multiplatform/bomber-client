package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

expect object Socials {
    val GitHub: Painter
        @Composable get
    val Telegram: Painter
        @Composable get
}