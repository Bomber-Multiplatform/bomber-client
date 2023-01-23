package com.kbomber.ui.components.picker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class CountryCodeVariant(
    val displayName: String,
    val icon: Painter,
    val countryCode: Int,
    val phoneBodySize: Int,
    val mask: String,
    val maskChar: Char
)

@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    countryCodes: List<CountryCodeVariant>,
    onCountryCodePicked: (CountryCodeVariant) -> Unit,
    isExpanded: Boolean = false,
    onDismiss: () -> Unit = {},
    offset: DpOffset = DpOffset(0.dp, 0.dp),
) {
    DropdownMenu(
        modifier = modifier,
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        offset = offset
    ) {
        for (code in countryCodes) {
            DropdownMenuItem(
                onClick = {
                    onCountryCodePicked(code)
                    onDismiss()
                }
            ) {
                Image(
                    modifier = Modifier.height(12.dp),
                    painter = code.icon,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(code.displayName, style = MaterialTheme.typography.body1)
            }
        }
    }
}