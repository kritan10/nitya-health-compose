package com.kritan.nityahealth.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun MyIconTextButton(
    icon: ImageVector,
    text: String? = null,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1F)
                )
                .padding(12.dp)
        ) {
            Icon(icon, text ?: "", tint = MaterialTheme.colorScheme.primary)
        }
        text?.let { text ->
            Spacer(Modifier.height(8.dp))
            Text(text, style = MaterialTheme.typography.labelMedium, fontSize = 10.sp)
        }
    }
}

@Composable
fun MyIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String = "",
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(icon, contentDescription)
    }
}

@Composable
fun MySelectionButton(
    modifier: Modifier = Modifier,
    label: String,
    isSelected: Boolean,
    height: Int = 52,
    width: Int = 0,
    shape: Shape = mRoundedCornerButton,
    onClick: () -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val bgColor = MaterialTheme.colorScheme.background

    val boxColor = if (isSelected) primaryColor else bgColor

    val sizeModifier = if (width == 0) {
        Modifier
            .fillMaxWidth()
            .height(height.dp)
    } else {
        Modifier.defaultMinSize(
            minWidth = width.dp,
            minHeight = height.dp
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(onClick = onClick)
            .then(sizeModifier)
            .border(1.dp, MaterialTheme.colorScheme.primary, shape)
            .background(boxColor, shape)
            .padding(horizontal = 12.dp)
    ) {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.W300)
    }
}


@Composable
fun MyRadioButton(label: String, selected: Boolean, onClick: () -> Unit) {
//    val color = MaterialTheme.colorScheme.primary
    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val targetColor =
            if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        val color by animateColorAsState(
            targetValue = targetColor,
            label = "radioColorChange"
        )
        Box(
            Modifier
                .size(15.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                .background(color = color, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(label)
    }
}