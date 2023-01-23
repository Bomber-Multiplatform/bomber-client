package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

expect object Flags {
    val Ukraine: Painter
        @Composable get
    val Russia: Painter
        @Composable get
    val Belarus: Painter
        @Composable get
    val Kazakhstan: Painter
        @Composable get
}