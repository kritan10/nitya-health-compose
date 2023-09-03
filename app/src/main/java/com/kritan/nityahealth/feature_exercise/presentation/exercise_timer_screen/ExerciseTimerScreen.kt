package com.kritan.nityahealth.feature_exercise.presentation.exercise_timer_screen

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.kritan.nityahealth.commons.components.MyButton
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.components.MyTimer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ExerciseTimerScreen(
    navigateUp: () -> Unit,
    viewModel: ExerciseTimerViewModel = hiltViewModel(),
    navigateToExerciseComplete: () -> Unit
) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Exercise", navigateUp = navigateUp)
    }) { pv ->
        if (viewModel.state.isLoading) {
            Box(
                Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        } else {
            val pagerState = rememberPagerState {
                viewModel.state.trainings.size
            }
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context).components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()

            fun moveToPreviousExercise() {
                coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage - 2)
                    viewModel.onExerciseChange()
                }
            }

            fun moveToNextExercise() {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    viewModel.onExerciseChange()
                }
            }

            LaunchedEffect(viewModel.state.timeLeft) {
                if (viewModel.state.timeLeft == 0) {
                    moveToNextExercise()
                }
            }

            HorizontalPager(
                modifier = Modifier.padding(top = pv.calculateTopPadding()),
                state = pagerState,
                userScrollEnabled = false
            ) { index ->
                with(viewModel.state.trainings[index]) {
                    val trainingType = this.trainingType?.lowercase()
                    if (trainingType == "rest") {
                        viewModel.onToggle(pause = false)
                        RestLayout(
                            timeLeft = viewModel.state.timeLeft,
                            isPaused = viewModel.state.isPaused,
                            onTick = viewModel::onTick,
                            moveToNextExercise = ::moveToNextExercise,
                            addRestTime = viewModel::onAddRestTime
                        )
                    } else {
                        ExerciseLayout(
                            imageLoader = imageLoader,
                            gifUrl = this.gif ?: "",
                            trainingName = this.trainingName ?: "",
                            reps = this.timer?.toInt() ?: 0,
                            isLast = index == (viewModel.state.trainings.size - 1),
                            moveToNextExercise = ::moveToNextExercise,
                            moveToPreviousExercise = ::moveToPreviousExercise,
                            navigateToExerciseComplete = navigateToExerciseComplete,
                            onTrainingComplete = viewModel::onTrainingComplete
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun RestLayout(
    timeLeft: Int,
    isPaused: Boolean,
    onTick: suspend () -> Unit,
    moveToNextExercise: () -> Unit,
    addRestTime: () -> Unit,
) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Rest")
        Spacer(Modifier.height(16.dp))
        MyTimer(timeLeft, isPaused, onTick)
        Spacer(modifier = Modifier.weight(1f))
        Row {
            MyButton(label = "+20", isFullLength = false, onClick = addRestTime)
            Spacer(Modifier.width(20.dp))
            MyButton(label = "Skip", isFullLength = false, onClick = moveToNextExercise)
        }
        Spacer(Modifier.height(10.dp))

    }
}

@Composable
private fun ExerciseLayout(
    imageLoader: ImageLoader,
    gifUrl: String,
    trainingName: String,
    reps: Int,
    isLast: Boolean,
    moveToNextExercise: () -> Unit,
    moveToPreviousExercise: () -> Unit,
    navigateToExerciseComplete: () -> Unit,
    onTrainingComplete: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = gifUrl,
            contentDescription = "",
            imageLoader = imageLoader,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.weight(1f))
        Text(trainingName)
        Text("x$reps")
        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.fillMaxWidth(), Arrangement.Center) {
            MyButton(
                label = "Previous",
                onClick = moveToPreviousExercise,
                isFullLength = false,
            )
            Spacer(modifier = Modifier.width(20.dp))
            if (!isLast) {
                MyButton(
                    label = "Done",
                    onClick = moveToNextExercise,
                    isFullLength = false,
                )
            } else {
                MyButton(
                    label = "Complete",
                    onClick = {
                        onTrainingComplete()
                        navigateToExerciseComplete()
                    },
                    isFullLength = false,
                )
            }
        }
        Spacer(Modifier.height(10.dp))
    }

}
