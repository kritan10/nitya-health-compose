package com.kritan.nityahealth.feature_auth.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kritan.nityahealth.commons.components.MyClickableText
import com.kritan.nityahealth.ui.theme.comfortaaFontFamily

@Composable
fun AuthFooter(text: String, buttonText: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.Center,
        Alignment.CenterVertically
    )
    {
        val footerTextStyle = TextStyle(
            fontSize = 12.sp,
            fontFamily = comfortaaFontFamily,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
        )
        Text(
            text, style = footerTextStyle
        )
        Spacer(modifier = Modifier.width(2.dp))
        MyClickableText(
            label = buttonText,
            onClick = onClick,
            style = footerTextStyle.copy(color = MaterialTheme.colorScheme.primary)
        )

    }
}