package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun FoodHomeScreen(viewModel: FoodViewModel, navigateToDashboard: () -> Unit) {
    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        MyScaffoldLayout(title = "Food", navigateUp = navigateToDashboard) {
            Text("This is food screen")
            MyButton(label = "Go back to dashboard", onClick = navigateToDashboard)

            viewModel.uiState.foodData?.let { foodList ->
                LazyColumn {
                    items(foodList) { food ->
                        Text(food.title)
                    }
                }
            }
        }
    }
}