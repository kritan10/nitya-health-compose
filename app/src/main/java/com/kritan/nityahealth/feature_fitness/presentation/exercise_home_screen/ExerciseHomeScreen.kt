package com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen

import android.os.Build
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.feature_fitness.data.models.ExercisePackage
import com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen.components.MyCalendar
import com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen.components.MyWeek
import com.kritan.nityahealth.ui.layouts.MyTitleBodyLayout
import com.kritan.nityahealth.ui.modifiers.mShadow
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseHomeScreen(
    viewModel: ExerciseHomeViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    navigateToExerciseList: (id: Int) -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Exercise", navigateUp = navigateUp)
    }) { pv ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = pv.calculateTopPadding(), start = 20.dp, end = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text("Welcome back", style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp))
            Text("Emma Parker", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                MyCalendar()
            }
            Spacer(modifier = Modifier.height(32.dp))


            if (viewModel.state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Week Goal")
                Spacer(modifier = Modifier.height(16.dp))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    MyWeek(viewModel.state.myTrainings)
                }
                Spacer(modifier = Modifier.height(16.dp))
                viewModel.state.myExercises.let { myExercise ->
                    Spacer(Modifier.height(16.dp))
                    MyTitleBodyLayout(title = "My Exercise") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(myExercise) { exercise ->
                                Spacer(Modifier.width(12.dp))
                                ExerciseCard(exercise, navigateToExerciseList)
                            }
                        }
                    }
                }

                viewModel.state.data.let { exercisePkg ->
                    Spacer(Modifier.height(16.dp))
                    MyTitleBodyLayout(title = "Exercise Set 1") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(exercisePkg.subList(0, 5)) { exercise ->
                                Spacer(Modifier.width(12.dp))
                                ExerciseCard(exercise, navigateToExerciseList)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    MyTitleBodyLayout(title = "Exercise Set 2") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(exercisePkg.subList(5, 10)) { exercise ->
                                Spacer(Modifier.width(12.dp))
                                ExerciseCard(exercise, navigateToExerciseList)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    MyTitleBodyLayout(title = "Exercise Set 3") {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            items(exercisePkg.subList(10, exercisePkg.size - 1)) { exercise ->
                                Spacer(Modifier.width(12.dp))
                                ExerciseCard(exercise, navigateToExerciseList)
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun ExerciseCard(exercise: ExercisePackage, navigateToExerciseList: (Int) -> Unit) {
    Column(
        Modifier
            .clickable {
                navigateToExerciseList(exercise.id!!)
            }
            .size(152.dp, 204.dp)
            .then(mShadow(shape = mRoundedCorner, elevation = 20))
    ) {

        AsyncImage(
            model = exercise.image,
            contentDescription = "",
            contentScale = ContentScale.Crop, modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        Column(Modifier.padding(10.dp)) {
            Text(
                trimString(exercise.title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W300)
            )
            Text(
                "05:30 minutes",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W300)
            )
            Text(
                "13 exercise",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W300)
            )
        }
    }
}

private fun trimString(string: String): String {
    val minLength = 13
    if (string.length < minLength) {
        return string
    }
    return string.substring(0, minLength) + "..."
}
