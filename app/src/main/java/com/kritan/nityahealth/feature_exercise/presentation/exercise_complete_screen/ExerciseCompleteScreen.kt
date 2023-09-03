package com.kritan.nityahealth.feature_exercise.presentation.exercise_complete_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kritan.nityahealth.commons.components.MyButton

@Composable
fun ExerciseCompleteScreen(navigateToExerciseHome: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text("Well Done", fontSize = 44.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))
        MyButton(label = "Go back home", onClick = navigateToExerciseHome)
    }
}