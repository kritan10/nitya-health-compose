package com.kritan.nityahealth.feature_doctor.presentation

import androidx.navigation.NavHostController
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

object DoctorsDestinations {
    const val DOCTORS_HOME_ROUTE = "home"
    const val DOCTORS_ALL_ROUTE = "all"
    const val DOCTORS_ADD_ROUTE = "new"
    const val DOCTORS_DETAILS_ROUTE = "details/"
}

class DoctorsNavigationActions(navController: NavHostController) {
    val navigateUp = NityaHealthNavigationActions(navController).navigateUp

    val navigateToDoctorsHomeAndClearBackStack: () -> Unit =
        {
            navController.navigate(DoctorsDestinations.DOCTORS_HOME_ROUTE) {
                popUpTo(DoctorsDestinations.DOCTORS_HOME_ROUTE) {
                    inclusive = true
                }
            }
        }

    val navigateToDoctorsAll: () -> Unit =
        { navController.navigate(DoctorsDestinations.DOCTORS_ALL_ROUTE) }

    val navigateToDoctorsDetail: (id: Int) -> Unit =
        { id -> navController.navigate(DoctorsDestinations.DOCTORS_DETAILS_ROUTE + "$id") }

    val navigateToAddDoctor: () -> Unit = {
        navController.navigate(DoctorsDestinations.DOCTORS_ADD_ROUTE)
    }

}