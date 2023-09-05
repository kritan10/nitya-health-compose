package com.kritan.nityahealth.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MyGridItemCard(title: String, @DrawableRes image: Int, navigateTo: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { navigateTo() }
            .background(
                MaterialTheme.colorScheme.primary.copy(0.1f),
                RoundedCornerShape(10.dp)
            )
            .padding(top = 20.dp, bottom = 24.dp)
    ) {
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(5.dp))
                .size(84.dp, 84.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
        Spacer(Modifier.height(20.dp))
        Text(title)
    }
}