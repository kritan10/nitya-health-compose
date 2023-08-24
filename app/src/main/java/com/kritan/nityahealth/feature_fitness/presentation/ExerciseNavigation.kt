package com.kritan.nityahealth.feature_fitness.presentation

import androidx.navigation.NavHostController
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

object ExerciseDestinations {
    const val EXERCISE_HOME_ROUTE = "exercise"
    const val EXERCISE_LIST_ROUTE = "exercise/{exercisePackageId}"
    const val EXERCISE_DETAIL_ROUTE = "exercise/{exercisePackageId}/{exerciseId}"
    const val EXERCISE_TIMER_ROUTE = "timer/{exercisePackageId}"
}


class ExerciseNavigationActions(navController: NavHostController) {
    val navigateUp = NityaHealthNavigationActions(navController).navigateUp

    val navigateToDashboard = NityaHealthNavigationActions(navController).navigateToDashboard

    val navigateToExerciseHome: () -> Unit = {
        navController.navigate(ExerciseDestinations.EXERCISE_HOME_ROUTE)
    }

    val navigateToExerciseList: (exercisePackageId: Int) -> Unit = { exercisePackageId ->
        navController.navigate(
            ExerciseDestinations.EXERCISE_LIST_ROUTE
                .replace("{exercisePackageId}", exercisePackageId.toString())
        )
    }

    val navigateToExerciseDetail: (exercisePackageId: Int, exerciseId: Int) -> Unit =
        { exercisePackageId, exerciseId ->
            navController.navigate(
                ExerciseDestinations.EXERCISE_DETAIL_ROUTE
                    .replace("{exercisePackageId}", exercisePackageId.toString())
                    .replace("{exerciseId}", exerciseId.toString())
            )
        }

    val navigateToExerciseTimer: (exercisePackageId: Int) -> Unit = { exercisePackageId ->
        navController.navigate(
            ExerciseDestinations.EXERCISE_TIMER_ROUTE
                .replace("{exercisePackageId}", exercisePackageId.toString())
        )
    }

}