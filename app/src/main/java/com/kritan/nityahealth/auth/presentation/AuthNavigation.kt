@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused")

package com.kritan.nityahealth.auth.presentation

import androidx.navigation.NavHostController
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

object AuthDestinations {
    const val ONBOARDING_INTRO_ROUTE = "intro"
    const val ONBOARDING_ROUTE = "onboard"
    const val SIGN_IN_ROUTE = "signin"
    const val SIGN_IN_EMAIL_ROUTE = "signin_email"
    const val SIGN_UP_ROUTE = "signup"
    const val SIGN_UP_LOCATION_ROUTE = "signup_location"
    const val SIGN_UP_VERIFY_ROUTE = "signup_verify"
}

object AuthRoutes {
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
}

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused")
class AuthNavigationActions(navController: NavHostController) {
    val navigateUp = NityaHealthNavigationActions(navController).navigateUp

//    val navigateToDashboard = NityaHealthNavigationActions(navController).navigateToDashboard

    val navigateToIntro: () -> Unit = {
        navController.navigate(AuthDestinations.ONBOARDING_INTRO_ROUTE)
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
            popUpTo(AuthRoutes.ONBOARDING) {
                inclusive = true
            }
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