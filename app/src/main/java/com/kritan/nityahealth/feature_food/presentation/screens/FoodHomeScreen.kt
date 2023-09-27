package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kritan.nityahealth.base.extensions.shortenString
import com.kritan.nityahealth.feature_food.data.models.Food
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout
import com.kritan.nityahealth.ui.layouts.MyTitleBodyLayout
import com.kritan.nityahealth.ui.modifiers.mShadow
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@Composable
fun FoodHomeScreen(viewModel: FoodViewModel, navigateToDashboard: () -> Unit) {
    MyLoadingLayout(loading = viewModel.uiState.isLoading) {
        MyScaffoldLayout(
            title = "Food",
            navigateUp = navigateToDashboard,
            requireScrolling = true
        ) {
            viewModel.uiState.foodData?.let { categorizedFoodList ->
                categorizedFoodList.forEachIndexed { index, foodList ->
                    val title = when (index) {
                        0 -> "Weight Gain Diet Plans"
                        1 -> "Weight Loss Diet Plans"
                        else -> "Popular Diet Plans"
                    }

                    MyTitleBodyLayout(title = title) {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(foodList) { food ->
                                Spacer(Modifier.width(12.dp))
                                FoodCard(food)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun FoodCard(food: Food) {
    Column(
        Modifier
            .size(152.dp, 204.dp)
            .then(mShadow(shape = mRoundedCorner, elevation = 20))
    ) {

        AsyncImage(
            model = food.image,
            contentDescription = "",
            contentScale = ContentScale.Crop, modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        Column(Modifier.padding(10.dp)) {
            Text(
                food.title.shortenString(11),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W300)
            )
            Text(
                "${(5..10).random()} recipes",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W300)
            )
        }
    }
}

