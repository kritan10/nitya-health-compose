package com.kritan.nityahealth.feature_fitness.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_fitness.presentation.exercise_complete_screen.ExerciseCompleteScreen
import com.kritan.nityahealth.feature_fitness.presentation.exercise_detail_screen.ExerciseDetailScreen
import com.kritan.nityahealth.feature_fitness.presentation.exercise_home_screen.ExerciseHomeScreen
import com.kritan.nityahealth.feature_fitness.presentation.exercise_list_screen.ExerciseListScreen
import com.kritan.nityahealth.feature_fitness.presentation.exercise_timer_screen.ExerciseTimerScreen
import com.kritan.nityahealth.ui.NityaHealthDestinations

fun NavGraphBuilder.exerciseGraph(navController: NavHostController) {
    val navigationActions = ExerciseNavigationActions(navController)
    navigation(
        route = NityaHealthDestinations.FITNESS_ROUTE,
        startDestination = ExerciseDestinations.EXERCISE_HOME_ROUTE,
    ) {
        composable(ExerciseDestinations.EXERCISE_HOME_ROUTE) {
            ExerciseHomeScreen(
                navigateUp = navigationActions.navigateUp,
                navigateToExerciseList = navigationActions.navigateToExerciseList
            )
        }

        composable(ExerciseDestinations.EXERCISE_LIST_ROUTE) {
            ExerciseListScreen(
                navigateUp = navigationActions.navigateUp,
                navigateToExerciseDetail = navigationActions.navigateToExerciseDetail,
                navigateToExerciseTimer = navigationActions.navigateToExerciseTimer
            )
        }

        composable(ExerciseDestinations.EXERCISE_DETAIL_ROUTE) {
            ExerciseDetailScreen(
                navigateUp = navigationActions.navigateUp,
            )

        }

        composable(ExerciseDestinations.EXERCISE_TIMER_ROUTE) {
            ExerciseTimerScreen(
                navigateUp = navigationActions.navigateUp,
                navigateToExerciseComplete = navigationActions.navigateToExerciseComplete
            )
        }

        composable(ExerciseDestinations.EXERCISE_COMPLETE_ROUTE){
            ExerciseCompleteScreen(
                navigateToExerciseHome = navigationActions.navigateToExerciseHome
            )
        }
    }
}