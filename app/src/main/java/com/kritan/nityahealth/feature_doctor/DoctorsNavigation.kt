package com.kritan.nityahealth.feature_doctor

import androidx.navigation.NavHostController
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

object DoctorsDestinations {
    const val DOCTORS_HOME_ROUTE = "home"
    const val DOCTORS_ALL_ROUTE = "all"
    const val DOCTORS_DETAILS_ROUTE = "details"
}

class DoctorsNavigationActions(navController: NavHostController) {
    val navigateUp = NityaHealthNavigationActions(navController).navigateUp

    val navigateToDoctorsHome: () -> Unit =
        { navController.navigate(DoctorsDestinations.DOCTORS_HOME_ROUTE) }

    val navigateToDoctorsAll: () -> Unit =
        { navController.navigate(DoctorsDestinations.DOCTORS_ALL_ROUTE) }

    val navigateToDoctorsDetail: () -> Unit =
        { navController.navigate(DoctorsDestinations.DOCTORS_DETAILS_ROUTE) }

}