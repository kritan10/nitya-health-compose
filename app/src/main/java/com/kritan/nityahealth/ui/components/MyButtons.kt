package com.kritan.nityahealth.commons.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    icon: ImageVector,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
        .padding(bottom = 5.dp)
        .align(Alignment.CenterVertically)
) {
    Icon(
        icon,
        contentDescription,
        modifier,
        tint
    )
}

@Composable
fun MyTextButton(
    label: String,
    textColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    leading: ImageVector? = null,
    trailing: ImageVector? = null,
    iconTint: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
            if (leading != null) MyButtonIcon(leading, label, iconTint)
            Text(label, color = textColor, style = textStyle)
            if (trailing != null) MyButtonIcon(trailing, label, iconTint)
        }
    }
}

@Deprecated("Use MyTextButton")
@Composable
fun MyTextWithIconButton(
    label: String,
    leading: ImageVector? = null,
    trailing: ImageVector? = null,
    iconColor: Color = Color.White,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
    Box(modifier = modifier) {
        TextButton(onClick = onClick) {
            if (leading != null) Icon(
                leading,
                label,
                Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically),
                Color.White
            )
            Text(text = label, modifier = Modifier.align(Alignment.CenterVertically))
            if (trailing != null) Icon(
                trailing,
                label,
                Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically),
                Color.White
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
    height: Int = 50,
    shape: Shape = mRoundedCornerButton,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        val buttonModifier =
            if (isFullLength) Modifier
                .fillMaxWidth()
                .height(height.dp)
            else Modifier.height(height.dp)

        Button(
            onClick = onClick,
            modifier = buttonModifier,
            shape = shape,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ) {
            if (leading != null) MyButtonIcon(leading, label, iconColor)
            Spacer(modifier = Modifier.width(10.dp))
            Text(label, color = textColor)
            Spacer(modifier = Modifier.width(10.dp))
            if (trailing != null) MyButtonIcon(trailing, label, iconColor)
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

