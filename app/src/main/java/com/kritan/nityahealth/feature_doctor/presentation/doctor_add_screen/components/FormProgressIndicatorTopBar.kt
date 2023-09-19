package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormProgressIndicatorTopBar(currentQuestion: Int, totalQuestionsCount: Int) {
    val animatedProgress by animateFloatAsState(
        label = "formProgress",
        targetValue = currentQuestion / (totalQuestionsCount - 1).toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )


    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // space is allocated by default from the column arrangement
        Spacer(Modifier)

        Text(
            "$currentQuestion out of ${totalQuestionsCount - 1}",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65F),
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )

        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
        )

        Spacer(Modifier)
    }
}