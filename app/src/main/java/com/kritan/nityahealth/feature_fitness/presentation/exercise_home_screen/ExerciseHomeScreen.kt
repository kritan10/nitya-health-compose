package com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.layouts.MyTitleBodyLayout
import com.kritan.nityahealth.ui.modifiers.mShadow
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseHomeScreen(navigateUp: () -> Unit, navigateToExerciseList: (Int) -> Unit) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Exercise", navigateUp = navigateUp)
    }) { pv ->
        Column(Modifier.padding(top = pv.calculateTopPadding())) {
            Text("Welcome back")
            Text("Emma Parker")
            Text("Calendar")
            Text("Week Goal")
            Text("Week days")
            Spacer(modifier = Modifier.height(16.dp))

            MyTitleBodyLayout(title = "Most Popular") {
                LazyRow() {
                    items(4) {
                        Spacer(Modifier.width(12.dp))
                        ExerciseCard(navigateToExerciseList)
                    }
                }
            }
        }
    }
}

@Composable
private fun ExerciseCard(navigateToExerciseList: (Int) -> Unit) {
    Column(
        Modifier
            .clickable {
                navigateToExerciseList(1)
            }
            .size(152.dp, 204.dp)
            .then(mShadow(shape = mRoundedCorner))
    ) {

        AsyncImage(
            model = "https://s3-alpha-sig.figma.com/img/2793/2d62/62b2c5f715d89eb427d7295022817ff3?Expires=1693785600&Signature=o5Gbr95AB1-HfFgyFRV6q~W7~Cp~Pl80vZTTU8C4g~WZ~FZKHkFKc9N2hx7OjWlDbMNDxpvBOTwkyBeZCsQW0uYWAt2U8pb7bsYd4az8rqsGCnXmf5hv8SfNeTxczVwp6VW9Qo31r8kFuM92ZB-eOOBelCi--u67Vrzqd3iZprGJXE0w-uPo2tCqkyGJkEm0cKLmNoDz4pflmMOXZ4uvb8Iq7gsRN~t-NpUoe6jAFbYROYB3CtLU91exJUHA5SZlHxiIyHsSWZUNXKVFu3AG3UfnPEtZ3SvPMSqqRf3RXx4MID2ywq1lgzKUOLIx1Iee943d5WDNINPZO1NFsk5TMA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            contentDescription = "",
            contentScale = ContentScale.Crop, modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        Column() {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Core Fitness")
            Text("05:30 minutes")
            Text("13 exercise")
        }
    }
}
