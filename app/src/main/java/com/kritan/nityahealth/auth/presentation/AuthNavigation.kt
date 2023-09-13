@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused")

package com.kritan.nityahealth.auth.presentation

import androidx.navigation.NavHostController
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

object AuthDestinations {
    const val INTRO_ROUTE = "intro"
    const val ONBOARDING_ROUTE = "onboarding"
    const val SIGN_IN_ROUTE = "signin"
    const val SIGN_IN_EMAIL_ROUTE = "signin_email"
    const val SIGN_UP_ROUTE = "signup"
    const val SIGN_UP_LOCATION_ROUTE = "signup_location"
    const val SIGN_UP_VERIFY_ROUTE = "signup_verify"
}

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused")
class AuthNavigationActions(navController: NavHostController) {
    val navigateUp = NityaHealthNavigationActions(navController).navigateUp

//    val navigateToDashboard = NityaHealthNavigationActions(navController).navigateToDashboard

    val navigateToIntro: () -> Unit = {
        navController.navigate(AuthDestinations.INTRO_ROUTE)
    }
    val navigateToOnboarding: () -> Unit = {
        navController.navigate(AuthDestinations.ONBOARDING_ROUTE)
    }
    val navigateToSignIn: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_IN_ROUTE)
    }
    val navigateToSignInEmail: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_IN_EMAIL_ROUTE)
    }
    val navigateToSignInEmailAndClearBackStack: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_IN_EMAIL_ROUTE) {
            popUpTo(AuthDestinations.SIGN_IN_ROUTE)
        }
    }
    val navigateToSignUp: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_UP_ROUTE)
    }
    val navigateToSignUpLocation: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_UP_LOCATION_ROUTE)
    }
    val navigateToSignUpVerify: () -> Unit = {
        navController.navigate(AuthDestinations.SIGN_UP_VERIFY_ROUTE)
    }


}