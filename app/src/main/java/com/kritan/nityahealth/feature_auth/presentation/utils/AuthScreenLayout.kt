package com.kritan.nityahealth.feature_auth.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenLayout(
    title: String,
    subtitle: String? = null,
    onNavigateUp: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        "",
                        Modifier.size(50.dp),
                        MaterialTheme.colorScheme.primary
                    )
                }
            },
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        )
    }) { pv ->
        Column(
            Modifier
                .padding(top = pv.calculateTopPadding(), start = 20.dp, end = 20.dp)
                .verticalScroll(rememberScrollState()),
            Arrangement.spacedBy(20.dp),
            Alignment.CenterHorizontally,
        ) {
            Text(title, Modifier.padding(bottom = 4.dp))
            if (subtitle != null) {
                Text(subtitle, Modifier.padding(bottom = 4.dp))
            }
            content()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}