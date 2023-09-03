package com.kritan.nityahealth.feature_exercise.presentation.exercise_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kritan.nityahealth.commons.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(navigateUp: () -> Unit,) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Core Fitness - Easy Pose", navigateUp = navigateUp)
    }) { pv ->
        Column(Modifier.padding(top = pv.calculateTopPadding() - 8.dp)) {
            AsyncImage(
                model = "https://s3-alpha-sig.figma.com/img/2793/2d62/62b2c5f715d89eb427d7295022817ff3?Expires=1693785600&Signature=o5Gbr95AB1-HfFgyFRV6q~W7~Cp~Pl80vZTTU8C4g~WZ~FZKHkFKc9N2hx7OjWlDbMNDxpvBOTwkyBeZCsQW0uYWAt2U8pb7bsYd4az8rqsGCnXmf5hv8SfNeTxczVwp6VW9Qo31r8kFuM92ZB-eOOBelCi--u67Vrzqd3iZprGJXE0w-uPo2tCqkyGJkEm0cKLmNoDz4pflmMOXZ4uvb8Iq7gsRN~t-NpUoe6jAFbYROYB3CtLU91exJUHA5SZlHxiIyHsSWZUNXKVFu3AG3UfnPEtZ3SvPMSqqRf3RXx4MID2ywq1lgzKUOLIx1Iee943d5WDNINPZO1NFsk5TMA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height((320 - 64).dp)
            )
            Text("Easy Pose")

            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Natoque nibh suscipit massa arcu magna. In diam faucibus cursus convallis velit, posuere. Urna diam montes, cursus mollis rhoncus rhoncus. Volutpat eget ultrices purus dictumst at sit fermentum id. Viverra leo ipsum purus maecenas. Condimentum nibh natoque mi mattis amet, a, id. Purus sed ullamcorper..")
        }
    }
}