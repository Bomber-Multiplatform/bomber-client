package com.kbomber.ui.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kbomber.ui.components.picker.CountryCodePicker
import com.kbomber.ui.components.picker.CountryCodeVariant
import com.kbomber.ui.localization.LocalStrings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhoneInputField(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onCountryCodeChange: (Int) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    countryCodes: List<CountryCodeVariant>
) = Column(
    modifier.then(
        Modifier.heightIn(TextFieldDefaults.MinHeight)
    )
) {

    val (currentCountryCode, setCountryCode) =
        remember { mutableStateOf(countryCodes.first()) }
    val (countryCodeIsVisible, setCountryCodeIsVisible) = remember {
        mutableStateOf(false)
    }

    val (isError, setError) = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            val interactionSource = MutableInteractionSource()
            Row(
                modifier = Modifier.clickable(interactionSource, null, true) {
                    setCountryCodeIsVisible(!countryCodeIsVisible)
                }.hoverable(interactionSource),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.padding(horizontal = 8.dp).height(12.dp),
                    painter = currentCountryCode.icon,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.focusable(false),
                    text = currentCountryCode.displayName,
                    style =
                    MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.onBackground
                    ),
                    overflow = TextOverflow.Visible
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    modifier = Modifier.focusable(false)
                        .indication(interactionSource, null),
                    onClick = { setCountryCodeIsVisible(!countryCodeIsVisible) }
                ) {
                    Icon(Icons.Rounded.ArrowDropDown, null)
                }
            }
        },
        value = phoneNumber,
        onValueChange = { newValue ->
            val formatted = newValue.trim()
            setError(formatted.any { !it.isDigit() })
            onPhoneNumberChange(newValue.take(
                currentCountryCode.mask.count { it == currentCountryCode.maskChar })
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        isError = isError,
        label = {
            Text(
                text = LocalStrings.current.phoneNumber,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        visualTransformation = PhoneVisualTransformation(
            currentCountryCode.mask,
            currentCountryCode.maskChar
        ),
        maxLines = 1
    )

    AnimatedVisibility(isError) {
        Text(
            text = LocalStrings.current.phoneNumberIsInvalid,
            style = TextStyle(Color.Red)
        )
    }

    CountryCodePicker(
        countryCodes = countryCodes,
        onCountryCodePicked = {
            setCountryCode(it)
            onCountryCodeChange(it.countryCode)
        },
        isExpanded = countryCodeIsVisible,
        onDismiss = { setCountryCodeIsVisible(false) }
    )
}

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        return maskNumber == other.maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar }
}

