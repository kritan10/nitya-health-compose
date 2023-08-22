package com.kritan.nityahealth.feature_auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.commons.components.MyTextWithIconButton

@Composable
fun WelcomeScreen(navigateToBoarding: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Image(
                    painterResource(R.drawable.main_icon), "",
                    Modifier
                        .height(150.dp)
                        .width(150.dp)
                )
                Text("Welcome to", style = MaterialTheme.typography.bodyMedium)
                Text("Nitya Health")
            }
            MyTextWithIconButton(
                label = "Get Started",
                trailing = Icons.Default.ArrowForward,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 50.dp, end = 36.dp)
            ) {
                navigateToBoarding()
            }
        }
    }
}

