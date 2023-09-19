package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.components.FormProgressIndicatorTopBar
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun DoctorAddScreen(
    viewModel: DoctorAddViewModel,
    navigateUp: () -> Unit,
    navigateToDoctorsHome: () -> Unit,
) {
    MyScaffoldLayout(title = "Add Doctor", navigateUp = navigateUp) {
        val currentQuestion = viewModel.questionIndex
        val totalQuestionsCount = viewModel.questionOrder.size

        if (currentQuestion != 0)
            FormProgressIndicatorTopBar(
                currentQuestion = currentQuestion,
                totalQuestionsCount = totalQuestionsCount
            )

        Box(
            Modifier
                .weight(1F)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            FormData(doctorAddViewModel = { viewModel }, targetQuestionIndex = currentQuestion)
        }

        Divider(Modifier.height(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val shouldDisplayPreviousButton = currentQuestion != 0 && currentQuestion != 4
            val nextButtonLabel = when (currentQuestion) {
                0 -> "Begin"
                4 -> "Complete"
                else -> "Next"
            }

            if (shouldDisplayPreviousButton)
                MyButton(
                    label = "Previous",
                    onClick = viewModel::moveToPreviousQuestion,
                    isFullLength = false,
                    enabled = true
                )

            MyButton(
                label = nextButtonLabel,
                onClick = if (currentQuestion == 4) navigateToDoctorsHome else viewModel::moveToNextQuestion,
                isFullLength = !shouldDisplayPreviousButton,
                enabled = viewModel.canMoveToNextQuestion()
            )
        }
    }
}



