package com.kritan.nityahealth.feature_auth.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_auth.presentation.screens.OnBoardingScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.SignInScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.SignUpLocationScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.SignUpVerifyScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.WelcomeScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.signin_email.SignInEmailScreen
import com.kritan.nityahealth.feature_auth.presentation.screens.signup.SignUpScreen

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
) {
    val navigationActions = AuthNavigationActions(navController)

    navigation(startDestination = AuthDestinations.INTRO_ROUTE, route = "auth") {

        composable(AuthDestinations.INTRO_ROUTE) {
            WelcomeScreen(navigateToBoarding = navigationActions.navigateToOnboarding)
        }

        composable(AuthDestinations.ONBOARDING_ROUTE) {
            OnBoardingScreen(navigateToLogin = navigationActions.navigateToSignIn)
        }
        composable(AuthDestinations.SIGN_IN_ROUTE) {
            SignInScreen(onSignInEmail = navigationActions.navigateToSignInEmail)
        }

        composable(AuthDestinations.SIGN_IN_EMAIL_ROUTE) {
            SignInEmailScreen(
                onNavigateUp = navigationActions.navigateUp,
                onNavigateToSignUp = navigationActions.navigateToSignUp,
            )
        }

        composable(AuthDestinations.SIGN_UP_ROUTE) {
            SignUpScreen(
                onNavigateUp = navigationActions.navigateUp,
                onNavigateToSignIn = navigationActions.navigateToSignIn,
            )
        }

        composable(AuthDestinations.SIGN_UP_VERIFY_ROUTE) {
            SignUpVerifyScreen(
                onNavigateUp = navigationActions.navigateUp,
                navigateToLocationPicker = navigationActions.navigateToSignUpLocation
            )
        }

        composable(AuthDestinations.SIGN_UP_LOCATION_ROUTE) {
            SignUpLocationScreen(onNavigateUp = navigationActions.navigateUp)
        }
    }
}