package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

actual object Flags {
    actual val Ukraine: Painter
        @Composable get() = painterResource("ukraine.png")
    actual val Russia: Painter
        @Composable get() = painterResource("russia.png")
    actual val Belarus: Painter
        @Composable get() =
        painterResource("belarus.png")
    actual val Kazakhstan: Painter
        @Composable get() = painterResource("kazakhstan.png")
}