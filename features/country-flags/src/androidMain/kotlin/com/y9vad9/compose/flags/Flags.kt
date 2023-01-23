package com.y9vad9.compose.flags

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.y9vad9.pomodoro.client.R

actual object Flags {
    actual val Ukraine: Painter
        @Composable get() = painterResource(R.drawable.ukraine)
    actual val Russia: Painter
        @Composable get() = painterResource(R.drawable.russia)
    actual val Belarus: Painter
        @Composable get() = painterResource(R.drawable.belarus)
    actual val Kazakhstan: Painter
        @Composable get() = painterResource(R.drawable.kazakhstan)
}