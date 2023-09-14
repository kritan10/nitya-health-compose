package com.kritan.nityahealth.feature_onboarding.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardingLayout(
    onboardingItem: OnboardingItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(bottom = 100.dp)
    ) {
        Image(
            painter = painterResource(onboardingItem.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(onboardingItem.label, textAlign = TextAlign.Center)
    }
}