package com.kritan.nityahealth.commons.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MyTitleBodyLayout(title: String, body: @Composable () -> Unit) {
    Column {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { }
        )
        Spacer(modifier = Modifier.height(16.dp))
        body()

    }
}