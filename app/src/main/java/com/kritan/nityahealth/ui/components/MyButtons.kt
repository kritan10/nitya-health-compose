package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.theme.mRoundedCornerButton

@Composable
private fun RowScope.MyButtonIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    tint: Color,
) {
    val iconModifier = modifier
        .padding(bottom = 5.dp)
        .align(Alignment.CenterVertically)
    Icon(
        icon,
        contentDescription,
        iconModifier,
        tint
    )
}

@Composable
fun MyTextButton(
    modifier: Modifier = Modifier,
    label: String,
    textColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    leading: ImageVector? = null,
    trailing: ImageVector? = null,
    iconTint: Color = Color.White,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
            if (leading != null) MyButtonIcon(
                icon = leading,
                contentDescription = label,
                tint = iconTint
            )
            Text(label, color = textColor, style = textStyle)
            if (trailing != null) MyButtonIcon(
                icon = trailing,
                contentDescription = label,
                tint = iconTint
            )
        }
    }
}

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    label: String,
    leading: ImageVector? = null,
    trailing: ImageVector? = null,
    isFullLength: Boolean = true,
    enabled: Boolean = true,
    height: Int = 52,
    shape: Shape = mRoundedCornerButton,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        val buttonModifier = Modifier
            .height(height.dp)
            .then(
                if (isFullLength) Modifier.fillMaxWidth() else Modifier
            )

        Button(
            onClick = onClick,
            modifier = buttonModifier,
            shape = shape,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                disabledContainerColor = Color.LightGray,
            )
        ) {
            if (leading != null) MyButtonIcon(
                icon = leading,
                contentDescription = label,
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(label, color = if (enabled) textColor else Color.Gray)
            Spacer(modifier = Modifier.width(10.dp))
            if (trailing != null) MyButtonIcon(
                icon = trailing,
                contentDescription = label,
                tint = iconColor
            )
        }
    }
}

@Composable
fun MyClickableText(
    label: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    onClick: () -> Unit
) {
    Text(label, Modifier.clickable { onClick() }, style = style)
}

