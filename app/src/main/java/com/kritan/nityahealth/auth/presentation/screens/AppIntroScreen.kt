package com.kritan.nityahealth.auth.presentation.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MyTextButton
import com.kritan.nityahealth.ui.theme.mySlideUpTransition
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navigateToBoarding: () -> Unit) {
    var isReady by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(500)
        isReady = true
    }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
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
            AnimatedVisibility(
                visible = isReady,
                modifier = Modifier.align(Alignment.BottomEnd),
                enter = mySlideUpTransition()
            ) {
                MyTextButton(
                    label = "Get Started",
                    trailing = Icons.Default.ArrowForward,
                    modifier = Modifier
                        .padding(bottom = 50.dp, end = 36.dp)
                ) {
                    navigateToBoarding()
                }
            }
        }
    }
}


