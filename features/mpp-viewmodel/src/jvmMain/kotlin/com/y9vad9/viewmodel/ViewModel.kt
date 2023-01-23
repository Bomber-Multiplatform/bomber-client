package com.y9vad9.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual abstract class ViewModel {
    actual val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
}