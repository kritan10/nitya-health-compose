package com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kritan.nityahealth.feature_exercise.data.models.ExercisePackage
import com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen.components.MyCalendar
import com.kritan.nityahealth.feature_exercise.presentation.exercise_home_screen.components.MyWeek
import com.kritan.nityahealth.ui.layouts.MyLoadingLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout
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
    MyScaffoldLayout(title = "Exercise", navigateUp = navigateUp) {
        LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp)) {
            item {
                Column {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "Welcome back",
                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 12.sp)
                    )
                    Text("Emma Parker", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        MyCalendar()
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            item {
                MyLoadingLayout(viewModel.state.isLoading) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Column {
                                Text("Week Goal")
                                Spacer(modifier = Modifier.height(16.dp))
                                MyWeek(viewModel.state.myTrainings)
                            }
                        }

                        viewModel.state.myExercises.let { myExercises ->
                            if (myExercises.isNotEmpty())
                                ExerciseRow(
                                    title = "Completed Exercise Sets",
                                    exercisePkg = myExercises,
                                    navigateToExerciseList = navigateToExerciseList
                                )
                        }

                        viewModel.state.data.let { allExercises ->
                            ExerciseRow(
                                title = "Exercise Set 1",
                                exercisePkg = allExercises.subList(0, 5),
                                navigateToExerciseList = navigateToExerciseList
                            )

                            ExerciseRow(
                                title = "Exercise Set 2",
                                exercisePkg = allExercises.subList(5, 10),
                                navigateToExerciseList = navigateToExerciseList
                            )

                            ExerciseRow(
                                title = "Exercise Set 3",
                                exercisePkg = allExercises.subList(10, allExercises.size - 1),
                                navigateToExerciseList = navigateToExerciseList
                            )
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

@Composable
private fun ExerciseRow(
    title: String,
    exercisePkg: List<ExercisePackage>,
    navigateToExerciseList: (Int) -> Unit,
) {
    MyTitleBodyLayout(title = title) {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(exercisePkg) { exercise ->
                Spacer(Modifier.width(12.dp))
                ExerciseCard(exercise, navigateToExerciseList)
            }
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
