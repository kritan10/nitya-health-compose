package com.kritan.nityahealth.feature_fitness.presentation.exercise_timer_screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.components.MyTimer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ExerciseTimerScreen(
    navigateUp: () -> Unit, viewModel: ExerciseTimerViewModel = hiltViewModel()
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

            LaunchedEffect(viewModel.state.timeLeft) {
                if (viewModel.state.timeLeft == 0) {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    delay(100)
                    viewModel.onExerciseChange(viewModel.state.trainings[pagerState.currentPage])
                }
            }
            Column(
                Modifier.padding(top = pv.calculateTopPadding()),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                HorizontalPager(state = pagerState, userScrollEnabled = false) {
                    Column {
                        AsyncImage(
                            model = viewModel.state.trainings[it].gif,
                            contentDescription = "",
                            imageLoader = imageLoader,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.height(16.dp))
                        viewModel.state.trainings[it].trainingName?.let { Text(it) }
                    }
                }

                Spacer(Modifier.height(16.dp))
                MyTimer(
                    viewModel.state.timeLeft,
                    viewModel.state.isPaused,
                    viewModel::onTick,
                )

                Row(Modifier.fillMaxWidth(0.8f), Arrangement.SpaceBetween) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                viewModel.onExerciseChange(viewModel.state.trainings[pagerState.currentPage])
                            }
                        },
                        modifier = Modifier.size(60.dp),
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(onClick = { viewModel.onToggle() }) {
                        Icon(
                            Icons.Default.PlayArrow,
                            "",
                            Modifier
                                .size(95.dp)
                                .clip(CircleShape)
                                .padding(8.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                viewModel.onExerciseChange(viewModel.state.trainings[pagerState.currentPage])
                            }
                        },
                        modifier = Modifier.size(60.dp),
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
