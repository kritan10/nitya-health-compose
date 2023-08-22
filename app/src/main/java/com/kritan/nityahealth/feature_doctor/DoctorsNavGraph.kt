package com.kritan.nityahealth.feature_doctor

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_doctor.screens.DoctorsAllScreen
import com.kritan.nityahealth.feature_doctor.screens.DoctorsScreen
import com.kritan.nityahealth.ui.NityaHealthDestinations

fun NavGraphBuilder.doctorsGraph(navController: NavHostController) {
    val navigationActions = DoctorsNavigationActions(navController)
    navigation(
        startDestination = DoctorsDestinations.DOCTORS_HOME_ROUTE,
        route = NityaHealthDestinations.DOCTORS_ROUTE
    ) {
        composable(DoctorsDestinations.DOCTORS_HOME_ROUTE) {
            DoctorsScreen(
                navigateUp = navigationActions.navigateUp,
                navigateToAllDoctors = navigationActions.navigateToDoctorsAll
            )
        }

        composable(DoctorsDestinations.DOCTORS_ALL_ROUTE) {
            DoctorsAllScreen(navigateUp = navigationActions.navigateUp)
        }

    }
}