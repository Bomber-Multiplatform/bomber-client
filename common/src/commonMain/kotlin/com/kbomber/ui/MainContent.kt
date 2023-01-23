package com.kbomber.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.kbomber.types.Phone
import com.kbomber.ui.components.input.PhoneInputField
import com.kbomber.ui.components.picker.CountryCodeVariant
import com.kbomber.ui.localization.LocalStrings
import com.y9vad9.compose.flags.Flags
import com.y9vad9.compose.flags.Socials

@Composable
fun MainContent(
    phone: Phone,
    setPhone: (Phone) -> Unit,
    isRunning: Boolean,
    startAttack: () -> Unit,
    stopAttack: () -> Unit,
    requestsCount: Int,
    cyclesCount: String,
    setCycleCount: (String) -> Unit
) {
    val (isCycleNumberInvalid, setIsCycleNumberInvalid) = remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp)
    ) {
        val (fieldsColumnSize, setFieldsColumnSize) = remember { mutableStateOf(IntSize.Zero) }

        Column(Modifier.fillMaxWidth().align(Alignment.TopCenter)
            .verticalScroll(rememberScrollState())
            .onSizeChanged {
                setFieldsColumnSize(it)
            }
        ) {
            Row {
                PhoneInputField(
                    modifier = Modifier.weight(4f),
                    phoneNumber = phone.body.string,
                    onCountryCodeChange = {
                        setPhone(
                            phone.copy(
                                countryCode = Phone.CountryCode(it)
                            )
                        )
                    },
                    onPhoneNumberChange = {
                        setPhone(phone.copy(
                            body = Phone.PhoneBody(it)
                        ))
                    },
                    countryCodes = listOf(
                        CountryCodeVariant(
                            LocalStrings.current.ukraine + " (+380)",
                            Flags.Ukraine,
                            380,
                            9,
                            "000-000-00-00",
                            '0'
                        ),
                        CountryCodeVariant(
                            LocalStrings.current.russia + " (+7)",
                            Flags.Russia,
                            7,
                            10,
                            "000-000-000",
                            '0'
                        )
                    )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(LocalStrings.current.countOfCycles)
                    },
                    value = cyclesCount,
                    onValueChange = { value ->
                        setIsCycleNumberInvalid(value.any { !it.isDigit() })
                        setCycleCount(value)
                    },
                    isError = isCycleNumberInvalid
                )
                AnimatedVisibility(isCycleNumberInvalid) {
                    Text(
                        LocalStrings.current.invalidCycleCount,
                        style = MaterialTheme.typography.caption.copy(
                            color = Color.Red
                        )
                    )
                }
            }

            AnimatedVisibility(isRunning) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = LocalStrings.current.requestsExecuted(requestsCount),
                    style = MaterialTheme.typography.caption
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = LocalStrings.current.description,
                style = MaterialTheme.typography.caption.copy(
                    color = Color.Gray
                )
            )

            Spacer(Modifier.height(60.dp))
        }

        Row(modifier = Modifier.height(60.dp).padding(top = 16.dp)
            .align(Alignment.BottomCenter)) {
            OutlinedButton(
                onClick = {},
                border = BorderStroke(0.dp, Color.Transparent)
            ) {
                Icon(Socials.GitHub, null)
            }

            Spacer(Modifier.width(8.dp))

            OutlinedButton(
                onClick = {},
                border = BorderStroke(0.dp, Color.Transparent)
            ) {
                Icon(Socials.Telegram, null)
            }

            Spacer(Modifier.width(16.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (isRunning)
                        stopAttack()
                    else startAttack()
                },
            ) {
                if (!isRunning) {
                    Icon(Icons.Rounded.PlayArrow, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(LocalStrings.current.startAttack, overflow = TextOverflow.Visible, maxLines = 1)
                } else {
                    Icon(Icons.Rounded.Stop, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(LocalStrings.current.endAttack, overflow = TextOverflow.Visible, maxLines = 1)
                }
            }
        }
    }

}
