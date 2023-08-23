package com.kritan.nityahealth.feature_doctor.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen.DoctorDetailScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctors_all_screen.DoctorsAllScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen.DoctorsScreen
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
                navigateToAllDoctors = navigationActions.navigateToDoctorsAll,
                navigateToDoctorDetails = navigationActions.navigateToDoctorsDetail
            )
        }

        composable(DoctorsDestinations.DOCTORS_ALL_ROUTE) {
            DoctorsAllScreen(navigateUp = navigationActions.navigateUp)
        }

        composable(
            DoctorsDestinations.DOCTORS_DETAILS_ROUTE + "{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            DoctorDetailScreen(navigateUp = navigationActions.navigateUp)
        }
    }
}