package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String = "",
    navigateUp: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    val iconButtonColors = IconButtonDefaults.iconButtonColors(
        contentColor = Color.White
    )

    CenterAlignedTopAppBar(
        title = { Text(title) },
        actions = { this.actions() },
        navigationIcon = {
            IconButton(
                onClick = navigateUp,
                modifier = Modifier.padding(20.dp),
                colors = iconButtonColors
            ) {
                Icon(Icons.Default.ArrowBack, "Back")
            }
        },
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomStart = 10.dp, bottomEnd = 10.dp
                )
            )
    )
}