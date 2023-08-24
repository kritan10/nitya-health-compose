package com.kritan.nityahealth.feature_fitness.presentation.exercise_timer_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.ui.components.MyTimer

@Composable
fun ExerciseTimerScreen(
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            MyTimer(
                totalTime = 10L * 1000L,
                handleColor = MaterialTheme.colorScheme.primary,
                inactiveBarColor = Color.DarkGray,
                activeBarColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7F),
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

