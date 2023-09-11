package com.kritan.nityahealth.auth.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AuthScreenLayout(
    title: String,
    subtitle: String? = null,
    onNavigateUp: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { AuthTopBar(onNavigateUp = onNavigateUp) },
    ) { pv ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Spacer(Modifier.height(pv.calculateTopPadding()))
                Text(title, Modifier.padding(bottom = 4.dp))
                if (subtitle != null) {
                    Text(subtitle, Modifier.padding(bottom = 4.dp))
                }
            }
            item {
                content()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}