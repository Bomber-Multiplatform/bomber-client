package com.kbomber.desktop

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.kbomber.types.Phone
import com.kbomber.ui.MainContent
import com.kbomber.ui.localization.LocalStrings
import com.kbomber.ui.localization.Strings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.awt.SystemTray
import kotlin.random.Random

fun main() = application {
    val (isAppVisible, setAppVisible) = remember { mutableStateOf(true) }

    if(isTraySupported) {
        Tray(
            painterResource("bomb.svg"),
            tooltip = "SMS Bomber",
            onAction = { setAppVisible(true) }
        ) {
            Item("Open SMS Bomber") {
                setAppVisible(true)
            }
            Item("Exist SMS Bomber") {
                exitApplication()
            }
        }
    } else {
        setAppVisible(true)
    }

    Window(
        visible = isAppVisible,
        title = "💣 SMS Bomber",
        state = WindowState(
            size = DpSize(400.dp, 600.dp),
            isMinimized = !isAppVisible
        ),
        onCloseRequest = { setAppVisible(false) }
    ) {

        MaterialTheme {
            CompositionLocalProvider(
                LocalStrings provides Strings.Russian
            ) {
                val (phone, setPhone) = remember {
                    mutableStateOf(
                        Phone(
                            Phone.CountryCode(380),
                            Phone.PhoneBody("678549528")
                        )
                    )
                }

                val (isRunning, setIsRunning) = remember {
                    mutableStateOf(false)
                }

                val (successAttempts, setSuccessAttempts) = remember {
                    mutableStateOf(0)
                }

                val (cyclesCount, setCyclesCount) = remember {
                    mutableStateOf("1")
                }

                MainContent(
                    phone,
                    setPhone,
                    isRunning,
                    {
                        setIsRunning(true)
                    },
                    {
                        setIsRunning(false)
                        setSuccessAttempts(0)
                    },
                    successAttempts,
                    cyclesCount,
                    setCyclesCount
                )

                if (isRunning) {
                    LaunchedEffect(false) {
                        delay(Random.nextLong(500, 2000))
                        setSuccessAttempts(successAttempts + Random.nextInt(1, 10))
                    }
                }
            }

        }
    }
}
