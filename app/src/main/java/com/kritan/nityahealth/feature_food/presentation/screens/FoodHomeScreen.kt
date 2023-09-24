package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun FoodHomeScreen(viewModel: FoodViewModel, navigateToDashboard: () -> Unit) {
    MyScaffoldLayout(title = "Food", navigateUp = navigateToDashboard) {
        Column(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text("This is food screen")
            MyButton(label = "Go back to dashboard", onClick = navigateToDashboard)
        }
    }
}