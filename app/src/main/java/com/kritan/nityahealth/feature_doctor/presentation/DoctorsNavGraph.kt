package com.kritan.nityahealth.feature_doctor.presentation

import androidx.activity.compose.BackHandler
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.DoctorAddScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.DoctorAddViewModel
import com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen.DoctorDetailScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctors_all_screen.DoctorsAllScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen.DoctorsHomeScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctors_home_screen.DoctorsScreenViewModel
import com.kritan.nityahealth.ui.NityaHealthDestinations

fun NavGraphBuilder.doctorsGraph(navController: NavHostController, navigateToSignIn: () -> Unit) {
    val navigationActions = DoctorsNavigationActions(navController)
    navigation(
        startDestination = DoctorsDestinations.DOCTORS_HOME_ROUTE,
        route = NityaHealthDestinations.DOCTORS_ROUTE
    ) {
        composable(DoctorsDestinations.DOCTORS_HOME_ROUTE) {
            val viewModel = hiltViewModel<DoctorsScreenViewModel>()

            DoctorsHomeScreen(
                viewModel = viewModel,
                navigateToAllDoctors = navigationActions.navigateToDoctorsAll,
                navigateToDoctorDetails = navigationActions.navigateToDoctorsDetail,
                navigateUp = navigationActions.navigateUp,
                navigateToAddDoctor = navigationActions.navigateToAddDoctor,
                navigateToSignIn = navigateToSignIn
            )
        }
    }

    composable(DoctorsDestinations.DOCTORS_ADD_ROUTE) {
        val viewModel: DoctorAddViewModel = hiltViewModel()

        fun onBack() {
            val didMoveToPrevQuestion = viewModel.moveToPreviousQuestion()
            if (!didMoveToPrevQuestion) {
                navigationActions.navigateUp()
            }
        }

        BackHandler(enabled = true) {
            onBack()
        }

        DoctorAddScreen(
            viewModel = viewModel,
            navigateUp = ::onBack,
            navigateToDoctorsHome = navigationActions.navigateToDoctorsHomeAndClearBackStack
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