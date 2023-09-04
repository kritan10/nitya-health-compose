package com.kritan.nityahealth.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    navigateToProfile: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = navigateToProfile) {
                Icon(Icons.Default.AccountCircle, "", Modifier.padding(20.dp))
            }
        },
        navigationIcon = {
            IconButton(navigateUp, Modifier.padding(20.dp)) {
                Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
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