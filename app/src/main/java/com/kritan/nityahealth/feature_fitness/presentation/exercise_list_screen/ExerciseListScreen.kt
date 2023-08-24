package com.kritan.nityahealth.feature_fitness.presentation.exercise_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.modifiers.mShadow
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListScreen(
    navigateUp: () -> Unit,
    navigateToExerciseDetail: (Int, Int) -> Unit,
    navigateToExerciseTimer: (Int) -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Exercise - Core Fitness", navigateUp = navigateUp)
    }) { pv ->
        Column(Modifier.padding(top = pv.calculateTopPadding() - 8.dp)) {
            Box {
                AsyncImage(
                    model = "https://s3-alpha-sig.figma.com/img/2793/2d62/62b2c5f715d89eb427d7295022817ff3?Expires=1693785600&Signature=o5Gbr95AB1-HfFgyFRV6q~W7~Cp~Pl80vZTTU8C4g~WZ~FZKHkFKc9N2hx7OjWlDbMNDxpvBOTwkyBeZCsQW0uYWAt2U8pb7bsYd4az8rqsGCnXmf5hv8SfNeTxczVwp6VW9Qo31r8kFuM92ZB-eOOBelCi--u67Vrzqd3iZprGJXE0w-uPo2tCqkyGJkEm0cKLmNoDz4pflmMOXZ4uvb8Iq7gsRN~t-NpUoe6jAFbYROYB3CtLU91exJUHA5SZlHxiIyHsSWZUNXKVFu3AG3UfnPEtZ3SvPMSqqRf3RXx4MID2ywq1lgzKUOLIx1Iee943d5WDNINPZO1NFsk5TMA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.height((320 - 64).dp)
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 36.dp, vertical = 12.dp)
                ) {
                    Text("Core Fitness", color = MaterialTheme.colorScheme.background)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("1:30 hours", color = MaterialTheme.colorScheme.background)
                        Text("13 exercises", color = MaterialTheme.colorScheme.background)
                    }
                }
            }
            LazyColumn(Modifier.padding(20.dp)) {
                items(2) {
                    ExerciseListItem(navigateToExerciseDetail)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item() {
                    MyButton(label = "Start") {
                        navigateToExerciseTimer(1)
                    }
                }
            }

        }
    }
}

@Composable
private fun ExerciseListItem(navigateToExerciseDetail: (Int, Int) -> Unit) {
    Row(
        Modifier
            .clickable {
                navigateToExerciseDetail(1, 1)
            }
            .height(88.dp)
            .then(mShadow(shape = mRoundedCorner, elevation = 20))
    ) {
        AsyncImage(
            model = "https://s3-alpha-sig.figma.com/img/2793/2d62/62b2c5f715d89eb427d7295022817ff3?Expires=1693785600&Signature=o5Gbr95AB1-HfFgyFRV6q~W7~Cp~Pl80vZTTU8C4g~WZ~FZKHkFKc9N2hx7OjWlDbMNDxpvBOTwkyBeZCsQW0uYWAt2U8pb7bsYd4az8rqsGCnXmf5hv8SfNeTxczVwp6VW9Qo31r8kFuM92ZB-eOOBelCi--u67Vrzqd3iZprGJXE0w-uPo2tCqkyGJkEm0cKLmNoDz4pflmMOXZ4uvb8Iq7gsRN~t-NpUoe6jAFbYROYB3CtLU91exJUHA5SZlHxiIyHsSWZUNXKVFu3AG3UfnPEtZ3SvPMSqqRf3RXx4MID2ywq1lgzKUOLIx1Iee943d5WDNINPZO1NFsk5TMA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(88.dp)
        )


        Divider(
            color = Color(0x33000000),
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 18.dp)
                .width(1.dp)
                .fillMaxHeight()
        )

        Column(Modifier.weight(1F)) {
            Text("Easy Pose")
            Text("Come into a seated position with ...")

        }

        IconButton(onClick = {}) {
            Icon(Icons.Default.KeyboardArrowRight, "")
        }

    }

}