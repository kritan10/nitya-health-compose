package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormProgressIndicatorTopBar(currentQuestion: Int, totalQuestionsCount: Int) {
    val animatedProgress by animateFloatAsState(
        label = "formProgress",
        targetValue = (currentQuestion + 1) / totalQuestionsCount.toFloat(),
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )
    Spacer(Modifier.height(12.dp))

    Text(
        "${currentQuestion + 1} out of $totalQuestionsCount",
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65F),
        fontSize = 10.sp,
        textAlign = TextAlign.Center
    )

    Spacer(Modifier.height(12.dp))

    LinearProgressIndicator(
        progress = animatedProgress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
    )

}