package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightGoal
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MySelectionButton
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun FoodGoalScreen(
    viewModel: FoodViewModel,
    navigateUp: () -> Unit,
    navigateToFoodUserDetail: () -> Unit,
) {
    MyScaffoldLayout(
        title = "Food",
        navigateUp = navigateUp,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
            Text(
                "Choose your goal",
                modifier = Modifier.padding(vertical = 4.dp),
                textAlign = TextAlign.Center
            )

            MySelectionButton(
                label = "Gain Weight",
                isSelected = viewModel.uiState.weightGoal == WeightGoal.GAIN,
                onClick = { viewModel.selectWeightGoal(WeightGoal.GAIN) }
            )
            MySelectionButton(
                label = "Lose Weight",
                isSelected = viewModel.uiState.weightGoal == WeightGoal.LOSE,
                onClick = { viewModel.selectWeightGoal(WeightGoal.LOSE) }
            )
            MySelectionButton(
                label = "Maintain Weight",
                isSelected = viewModel.uiState.weightGoal == WeightGoal.MAINTAIN,
                onClick = { viewModel.selectWeightGoal(WeightGoal.MAINTAIN) }
            )

            Spacer(modifier = Modifier.weight(1F))

            MyButton(
                label = "Next",
                enabled = viewModel.uiState.weightGoal != null,
                onClick = navigateToFoodUserDetail
            )
        }
    )
}